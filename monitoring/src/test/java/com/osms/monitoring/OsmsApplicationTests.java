package com.osms.monitoring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OsmsApplicationTests {

	@Test
	public void contextLoads() {

		try {
			// Heartbeat Test like ping...
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://www.naver.com");
			HttpResponse response = client.execute(request);

			BufferedReader bufReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuilder builder = new StringBuilder();

			String line;

			while ((line = bufReader.readLine()) != null) {
				builder.append(line);
				builder.append(System.lineSeparator());
			}

			System.out.println(builder);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
