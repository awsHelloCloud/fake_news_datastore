package com.awscloudhello.fakenewsdatastore.controller;

import com.awscloudhello.fakenewsdatastore.model.Result;
import com.awscloudhello.fakenewsdatastore.service.FakeNewsService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeNewsControllerTest {

    @Tested
    private FakeNewsController unit;
    @Injectable
    private FakeNewsService service;

    @Test
    public void testIsFakeNews(){
        String text = "sample content";
        Result expectResult = new Result();
        new Expectations(){{
            service.isFakeNews(text);
            result = expectResult;
        }};
        Result result = unit.isFakeNews(text);
        assertEquals(expectResult, result);

    }


}
