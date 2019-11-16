package com.awscloudhello.fakenewsdatastore.service;

import mockit.Tested;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryStringServiceTest {

    @Tested
    private QueryStringService unit;

    @Test
    public void testGetQueryStringList(){
        String text = "Hello   I'm your LongestString";
        List<String> stringList = unit.getQueryStringList(text);
        assertEquals("LongestString", stringList.get(0));
        assertEquals("Hello", stringList.get(1));
        assertEquals("your", stringList.get(2));
        assertEquals("I'm", stringList.get(3));
    }
}
