package com.osms.monitoring.monitor.os2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "displayName",
        "mac",
        "ipv4",
        "bytesRecv",
        "bytesSent",
        "packetsRecv",
        "packetsSent",
        "inErrors"
})
public class Network {

    @JsonProperty("name")
    private String name;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("ipv4")
    private List<String> ipv4 = null;
    @JsonProperty("bytesRecv")
    private Long bytesRecv;
    @JsonProperty("bytesSent")
    private Long bytesSent;
    @JsonProperty("packetsRecv")
    private Long packetsRecv;
    @JsonProperty("packetsSent")
    private Long packetsSent;
    @JsonProperty("inErrors")
    private Long inErrors;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("mac")
    public String getMac() {
        return mac;
    }

    @JsonProperty("mac")
    public void setMac(String mac) {
        this.mac = mac;
    }

    @JsonProperty("ipv4")
    public List<String> getIpv4() {
        return ipv4;
    }

    @JsonProperty("ipv4")
    public void setIpv4(List<String> ipv4) {
        this.ipv4 = ipv4;
    }

    @JsonProperty("bytesRecv")
    public Long getBytesRecv() {
        return bytesRecv;
    }

    @JsonProperty("bytesRecv")
    public void setBytesRecv(Long bytesRecv) {
        this.bytesRecv = bytesRecv;
    }

    @JsonProperty("bytesSent")
    public Long getBytesSent() {
        return bytesSent;
    }

    @JsonProperty("bytesSent")
    public void setBytesSent(Long bytesSent) {
        this.bytesSent = bytesSent;
    }

    @JsonProperty("packetsRecv")
    public Long getPacketsRecv() {
        return packetsRecv;
    }

    @JsonProperty("packetsRecv")
    public void setPacketsRecv(Long packetsRecv) {
        this.packetsRecv = packetsRecv;
    }

    @JsonProperty("packetsSent")
    public Long getPacketsSent() {
        return packetsSent;
    }

    @JsonProperty("packetsSent")
    public void setPacketsSent(Long packetsSent) {
        this.packetsSent = packetsSent;
    }

    @JsonProperty("inErrors")
    public Long getInErrors() {
        return inErrors;
    }

    @JsonProperty("inErrors")
    public void setInErrors(Long inErrors) {
        this.inErrors = inErrors;
    }

    @Override
    public String toString() {
        return "ClassPojo [bytesSent = "+bytesSent+", bytesRecv = "+bytesRecv+", ipv4 = "+ipv4+", packetsSent = "+packetsSent+", packetsRecv = "+packetsRecv+", name = "+name+", mac = "+mac+", displayName = "+displayName+", inErrors = "+inErrors+"]";
    }
}
