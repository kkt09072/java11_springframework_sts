package com.spring1.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
    public static void main(String[] args) {
        // HttpClient 생성
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	
            // GET 요청 생성
            HttpGet request = new HttpGet("https://www.naver.com");

            // 요청 실행
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 응답 상태 코드 확인
                System.out.println(response.getStatusLine().getStatusCode());

                // 응답 엔티티 확인 및 출력
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}