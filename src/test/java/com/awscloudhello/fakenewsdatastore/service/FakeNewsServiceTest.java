package com.awscloudhello.fakenewsdatastore.service;

import com.awscloudhello.fakenewsdatastore.model.Article;
import com.awscloudhello.fakenewsdatastore.model.ArticleReply;
import com.awscloudhello.fakenewsdatastore.model.Result;
import com.awscloudhello.fakenewsdatastore.repository.ArticleReplyRepository;
import com.awscloudhello.fakenewsdatastore.repository.ArticleRepository;
import com.awscloudhello.fakenewsdatastore.service.QueryStringService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.awscloudhello.fakenewsdatastore.model.ArticleOM.newArticle;
import static com.awscloudhello.fakenewsdatastore.model.ArticleReplyOM.newArticleReply;
import static com.awscloudhello.fakenewsdatastore.service.FakeNewsService.RUMOR;
import static org.junit.jupiter.api.Assertions.*;

public class FakeNewsServiceTest {

    @Mocked
    private ArticleRepository articleRepository;
    @Mocked
    private ArticleReplyRepository articleReplyRepository;
    @Mocked
    private QueryStringService stringService;

    private FakeNewsService getUnit(){
        return new FakeNewsService(articleRepository, articleReplyRepository, stringService);
    }

    @Test
    public void testIsFakeNews(){
        FakeNewsService unit = this.getUnit();
        String replayType = RUMOR;
        String input = "apple bb, cc";
        String queryString = "%apple%";
        List<String> stringList = Arrays.asList("apple", "bb,", "cc");
        Article article = newArticle(input);
        List<Article> articleList = Collections.singletonList(article);
        ArticleReply reply = newArticleReply(replayType);
        List<ArticleReply> replies = Collections.singletonList(reply);
        new Expectations(){{
            stringService.getQueryStringList(input);
            result = stringList;

            articleRepository.findByTextLike(queryString);
            result = articleList;

            articleReplyRepository.findAllByArticleIdIn((List<String>) any);
            result = replies;

        }};
        Result result = unit.isFakeNews(input);
        assertTrue(result.isFakeNews());
        assertEquals(article.getId(), result.getArticleId());
    }

    @Test
    public void testIsFakeNews_notRumor(){
        FakeNewsService unit = this.getUnit();
        String replayType = "NotNumor";
        String input = "apple bb, cc";
        String queryString = "%apple%";
        List<String> stringList = Arrays.asList("apple", "bb,", "cc");
        Article article = newArticle(input);
        List<Article> articleList = Collections.singletonList(article);
        ArticleReply reply = newArticleReply(replayType);
        List<ArticleReply> replies = Collections.singletonList(reply);
        new Expectations(){{
            stringService.getQueryStringList(input);
            result = stringList;

            articleRepository.findByTextLike(queryString);
            result = articleList;

            articleReplyRepository.findAllByArticleIdIn((List<String>) any);
            result = replies;

        }};
        Result result = unit.isFakeNews(input);
        assertFalse(result.isFakeNews());
        assertEquals(article.getId(), result.getArticleId());
    }

    @Test
    public void testIsFakeNews_couldNotFoundArticle(){
        FakeNewsService unit = this.getUnit();
        String input = "apple bb, cc12312313123";
        List<String> stringList = Arrays.asList("cc12312313123", "apple", "bb,");
        String queryString = "%cc12312313123%";
        new Expectations(){{
            stringService.getQueryStringList(input);
            result = stringList;

            articleRepository.findByTextLike(queryString);
            result = Collections.EMPTY_LIST;

        }};
        Result result = unit.isFakeNews(input);
        assertFalse(result.isFakeNews());
        assertNull(result.getArticleId());
    }

    @Test
    public void testIsFakeNews_couldNotFoundReply(){
        FakeNewsService unit = this.getUnit();
        String input = "apple 1231231eeeerrr cc";
        List<String> stringList = Arrays.asList("1231231eeeerrr", "apple", "cc");
        String queryString = "%1231231eeeerrr%";
        Article article = newArticle(input);
        List<Article> articleList = Collections.singletonList(article);

        new Expectations(){{
            stringService.getQueryStringList(input);
            result = stringList;

            articleRepository.findByTextLike(queryString);
            result = articleList;

            articleReplyRepository.findAllByArticleIdIn((List<String>) any);
            result = Collections.EMPTY_LIST;;

        }};
        Result result = unit.isFakeNews(input);
        assertFalse(result.isFakeNews());
        assertNull(result.getArticleId());
    }

}
