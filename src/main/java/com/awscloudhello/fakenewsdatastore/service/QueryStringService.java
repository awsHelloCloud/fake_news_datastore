package com.awscloudhello.fakenewsdatastore.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class QueryStringService {

    public List<String> getQueryStringList(String text){
        String[] subStrings = text.split("\\s+");
        Arrays.sort(subStrings, Comparator.comparingInt(String::length).reversed());
        return Arrays.asList(subStrings);
    }
}
