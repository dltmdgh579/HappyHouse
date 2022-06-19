package com.ssafy.vue.mapper;

import java.util.List;

public interface NewsMapper {
    public List<String> getInterest(String userid);
    public void addInterest(String userid, String dongname);
    public void removeInterest(String userid, String dongname);
    public int checkInterest(String userid, String dongname);
}
