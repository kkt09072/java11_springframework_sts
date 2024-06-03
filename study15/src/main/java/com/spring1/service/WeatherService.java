package com.spring1.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring1.controller.WeatherController;
import com.spring1.dto.Weather;

@Service
public class WeatherService {

	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
	
    public String loadData(String serviceKey, String baseDate, String nx, String ny) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        log.info("before Parasing"+sb.toString());
        return sb.toString();
    }

    public JSONArray parsingData(String serviceKey, String baseDate, String nx, String ny) throws IOException, ParseException {
        String stream = this.loadData(serviceKey, baseDate, nx, ny);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stream);

        if (jsonObject == null || !jsonObject.containsKey("response")) {
            return new JSONArray();
        }

        JSONObject response = (JSONObject) jsonObject.get("response");
        if (response == null || !response.containsKey("body")) {
            return new JSONArray();
        }

        JSONObject body = (JSONObject) response.get("body");
        if (body == null || !body.containsKey("items")) {
            return new JSONArray();
        }

        JSONObject items = (JSONObject) body.get("items");
        if (items == null || !items.containsKey("item")) {
            return new JSONArray();
        }

        return (JSONArray) items.get("item");
    }

    public Weather resultData(String serviceKey, String baseDate, String nx, String ny) throws IOException, ParseException {
        JSONArray jsonArray = this.parsingData(serviceKey, baseDate, nx, ny);

        Weather dto = new Weather();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = (JSONObject) jsonArray.get(i);

            if (item == null) continue;

            String category = item.get("category").toString().toUpperCase();
            String obsrValue = item.get("obsrValue").toString();

            switch (category) {
                case "PTY":
                    dto.setPty(obsrValue);
                    break;
                case "REH":
                    dto.setReh(obsrValue);
                    break;
                case "RN1":
                    dto.setRn1(obsrValue);
                    break;
                case "T1H":
                    dto.setT1h(obsrValue);
                    break;
                case "UUU":
                    dto.setUuu(obsrValue);
                    break;
                case "VEC":
                    dto.setVec(obsrValue);
                    break;
                case "VVV":
                    dto.setVvv(obsrValue);
                    break;
                case "WSD":
                    dto.setWsd(obsrValue);
                    break;
                case "SKY":
                    dto.setSky(obsrValue);
                    break;
                default:
                    break;
            }
        }

        log.info("service : "+dto.toString());
        
        return dto;
    }
}