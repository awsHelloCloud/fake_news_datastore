package com.awscloudhello.fakenewsdatastore.controller;

import com.awscloudhello.fakenewsdatastore.model.Result;
import com.awscloudhello.fakenewsdatastore.service.FakeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeNewsController {

    private FakeNewsService service;

    @Autowired
    public FakeNewsController(FakeNewsService service){
        this.service = service;
    }


    @GetMapping("/isFakeNews")
    public Result isFakeNews(@RequestParam(value = "text") String text){
        boolean result = service.isFakeNews(text);
        return new Result(result);
    }
}
