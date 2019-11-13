package com.awscloudhello.fakenewsdatastore.model;

import lombok.Data;

@Data
public class Result {

    private String isFakeNews;

    public Result(boolean result){
        this.isFakeNews = String.valueOf(result);
    }
}
