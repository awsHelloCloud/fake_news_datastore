package com.awscloudhello.fakenewsdatastore.controller;

import com.awscloudhello.fakenewsdatastore.model.Result;
import com.awscloudhello.fakenewsdatastore.service.FakeNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FakeNewsController {

    private FakeNewsService service;

    @Autowired
    public FakeNewsController(FakeNewsService service){
        this.service = service;
    }


    @PostMapping("/isFakeNews")
    public Result isFakeNews(@RequestParam(value = "text") String text){
        log.info("text: " + text);
        Result result =  service.isFakeNews(text);
        log.info("result: " + result.getArticleId());
        return result;
    }
}
