package com.awscloudhello.fakenewsdatastore.model;

import lombok.Data;

@Data
public class Result {

    private String articleId;

    private boolean isFakeNews;

    public Result(){
    }

    public Result(String articleId){
        this.isFakeNews = true;
        this.articleId = articleId;
    }
}
