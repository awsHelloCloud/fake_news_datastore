package com.awscloudhello.fakenewsdatastore.model;

import lombok.Data;

@Data
public class Result {

    private String articleId;

    private Boolean fakeNews;

    public Result(){
    }

    public Result(String articleId, Boolean fakeNews){
        this.fakeNews = fakeNews;
        this.articleId = articleId;
    }
}
