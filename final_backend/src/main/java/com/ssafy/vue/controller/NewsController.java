package com.ssafy.vue.controller;

import com.ssafy.vue.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;


    @GetMapping("/{keyword}")
    public ResponseEntity<String> getNews(@PathVariable String keyword) {

        return new ResponseEntity<String>(newsService.getNews(keyword), HttpStatus.OK);
    }

    @GetMapping("/interest/{userid}")
    public ResponseEntity<List<String>> getInterest(@PathVariable String userid) {
        return new ResponseEntity<>(newsService.getInterest(userid), HttpStatus.OK);
    }

    @GetMapping("/interest/{userid}/{dongname}")
    public void addInterest(@PathVariable String userid, @PathVariable String dongname){
        newsService.addInterest(userid, dongname);
    }

    @DeleteMapping("/interest/{userid}/{dongname}")
    public void removeInterest(@PathVariable String userid, @PathVariable String dongname){
        newsService.removeInterest(userid, dongname);
    }

    @GetMapping("/check/{userid}/{dongname}")
    public int checkInterest(@PathVariable String userid, @PathVariable String dongname){
        return newsService.checkInterest(userid, dongname);
    }
}
