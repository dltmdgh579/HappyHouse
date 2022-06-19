package com.ssafy.vue.service;

import java.util.List;

public interface NewsService {
    String getNews(String keyword);
    List<String> getInterest(String userid);
    void addInterest(String userid, String dongname);
    void removeInterest(String userid, String dongname);
    int checkInterest(String userid, String dongname);
}
