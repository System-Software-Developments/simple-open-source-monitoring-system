package com.osms.monitoring.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Map;

import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OsmsUtils {

    private static final Logger logger = LoggerFactory.getLogger(OsmsUtils.class);

    public static String getDevicename(String devicename) {
        return getDevicename(devicename);
    }

    public static String getDevicename(String devicename, String...args) {
        if(args != null) {
            devicename = replaceTwoToOne(replaceSpecialCharacter(replace(devicename, args)));

        }
        String prefix = OsmsProperties.getPrefix();
        if (prefix != null && !prefix.isEmpty()) {
            devicename = prefix+OsmsProperties.getConcatenator()+devicename;
            //logger.debug("prefix : {}, getConcatenator : {}, devicename : {}", prefix, OsmsProperties.getConcatenator(), devicename);
        }
        return devicename;
    }

    public static String replace(String value, String...args) {
        for(String arg:args) {
            value = value.replace("{}", arg);
        }
        return value;
    }


    public static String replaceSpaceCharacter(String devicename) {
        return devicename.replaceAll("[\\s+]", "");
    }

    public static String replaceTwoToOne(String devicename) {
        return devicename.replaceAll(OsmsProperties.getConcatenator()+"{2,}", OsmsProperties.getConcatenator());
    }
    public static String replaceSpecialCharacter(String devicename) {
        return devicename.replaceAll("[^a-zA-Z0-9]", OsmsProperties.getConcatenator());
    }

    public static InetAddress getLocalAddress() throws SocketException {
        Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
        while(ifaces.hasMoreElements()) {

            NetworkInterface iface = ifaces.nextElement();
            Enumeration<InetAddress> addresses = iface.getInetAddresses();

            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if(addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                    return addr;
                }
            }
        }

        return null;
    }

    public static String getMacAddress() throws Exception{
        NetworkInterface network = NetworkInterface.getByInetAddress(getLocalAddress());

        byte[] mac = network.getHardwareAddress();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }

        return sb.toString();
    }

    public static HttpClient createHttpClient(String url) throws Exception {

        HttpClient httpClient = null;

        int timeout = 1;
        // ConnectionTimeout SocketTimeout 설정
        // http://www.baeldung.com/httpclient-timeout
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        if(url.toLowerCase().startsWith("https")) {

            TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] certificate, String authType) {
                    return true;
                }
            };

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            httpClient = HttpClients.custom().setSSLSocketFactory(csf).setDefaultRequestConfig(config).build();

        } else {
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        }

        return httpClient;

    }

    public static Map<String, Object> request(String target, String data) throws Exception {
        return request(target, data, null, null, true);
    }

    public static Map<String, Object> request(String target, String data, String authorization, Map<String, Object> body, boolean check) throws Exception {

        HttpPost httpPost = null;
        HttpResponse response = null;
        HttpClient httpClient = null;

        try {

            httpClient = OsmsUtils.createHttpClient(target);

            httpPost = new HttpPost(target);
            if(authorization != null) {
                httpPost.setHeader("Authorization", authorization);
            }
            if(data != null) {
                HttpEntity entity = new StringEntity(data);
                httpPost.setEntity(entity);
                httpPost.setHeader("Content-type", "text/plain");
            }

            if(body != null) {
                String convertObjToJsonStr = JsonUtils.convertObjToJsonStr(body);
                StringEntity input = new StringEntity(convertObjToJsonStr);
                input.setContentType("application/json");
                httpPost.setEntity(input);
            }

            logger.info("Requesting.......");
            logger.info("URI: {}", httpPost.getURI().toString());
            response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 200) {
                logger.error("HTTP error: {}", EntityUtils.toString(response.getEntity(), "UTF-8"));
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            String profile = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("Response : {}", profile);

            Map<String, Object> responseMap = (Map<String, Object>) JsonUtils.toObject(profile, Map.class);
            if(check) {
                if (!(Boolean) responseMap.get("Success")) {
                    throw new RuntimeException(String.format("Success: %s, Error message: %s",
                            responseMap.get("Success"), responseMap.get("Message")));
                }
            }
            return responseMap;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.getConnectionManager().shutdown();
                }
            } catch (Exception e) {
                logger.error("Error", e);
            }
        }
    }
}
