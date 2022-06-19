package com.ssafy.vue.service;

import com.ssafy.vue.mapper.NewsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public String getNews(String keyword) {
        String clientId = "VYNCyP8TwH8JjXNG5hP3";
        String clientSecret = "iMStMeVgte";
        String key=null;
        try {
            key = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("인코딩에러");
        }

        String apiURL = "https://openapi.naver.com/v1/search/news?query=" + key;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
//        JSONObject temp = new JSONObject(responseBody);
//        System.out.println(temp.get("items"));
        return responseBody;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try{
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header: requestHeaders.entrySet()){
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return readBody(con.getInputStream());
            }else{
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            System.out.println("요청-응답 에러");
            e.printStackTrace();
        }finally{
            con.disconnect();
        }
        return null;
    }
    private static HttpURLConnection connect(String apiUrl){
        try{
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (Exception e) {
            System.out.println("연결 중 에러 발생");
        }
        return null;
    }

    private static String readBody(InputStream body){
        InputStreamReader sr = new InputStreamReader(body);
        try(BufferedReader br = new BufferedReader(sr)){
            StringBuilder responseBody = new StringBuilder();
            String line;
            while((line=br.readLine())!=null){
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            System.out.println("읽는중 오류 발생");
        }
        return null;
    }

    @Override
    public List<String> getInterest(String userid) {
        return newsMapper.getInterest(userid);
    }

    @Override
    public void addInterest(String userid, String dongname) {
        newsMapper.addInterest(userid, dongname);
    }

    @Override
    public void removeInterest(String userid, String dongname) {
        newsMapper.removeInterest(userid, dongname);
    }

    @Override
    public int checkInterest(String userid, String dongname) {
        return newsMapper.checkInterest(userid, dongname);
    }
}
