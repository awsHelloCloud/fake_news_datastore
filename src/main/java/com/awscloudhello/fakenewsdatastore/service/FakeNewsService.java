package com.awscloudhello.fakenewsdatastore.service;

import com.awscloudhello.fakenewsdatastore.model.Article;
import com.awscloudhello.fakenewsdatastore.model.ArticleReply;
import com.awscloudhello.fakenewsdatastore.model.Result;
import com.awscloudhello.fakenewsdatastore.repository.ArticleReplyRepository;
import com.awscloudhello.fakenewsdatastore.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FakeNewsService {

    private static final String RUMOR = "RUMOR";

    private ArticleRepository articleRepository;
    private ArticleReplyRepository articleReplyRepository;

    @Autowired
    public FakeNewsService(ArticleRepository articleRepository, ArticleReplyRepository articleReplyRepository){
        this.articleRepository = articleRepository;
        this.articleReplyRepository = articleReplyRepository;
    }

    public Result isFakeNews(String text){
        String[] subStrings = text.split("\\s+");
        Arrays.sort(subStrings, Comparator.comparingInt(String::length).reversed());
        List<String> stringList = Arrays.asList(subStrings);
        List<Article> articleList = articleRepository.findByTextLike("%" + stringList.get(0) + "%");
        List<Article> filteredArticles = articleList.stream()
                .filter(article -> this.isTheArticle(article, stringList))
                .collect(Collectors.toList());

        if(!filteredArticles.isEmpty()){
            List<String> articleIds = filteredArticles.stream().map(Article::getId).collect(Collectors.toList());
            List<ArticleReply> articleReplies = articleReplyRepository.findAllByArticleIdIn(articleIds);
            Map<String, List<ArticleReply>> articleReplyMap = this.getArticleReplyMap(articleReplies);
            for(Map.Entry<String, List<ArticleReply>> entry : articleReplyMap.entrySet()){
                for(ArticleReply reply : entry.getValue()){
                    if(RUMOR.equals(reply.getReplyType())){
                        return new Result(entry.getKey());
                    }
                }
            }
        }
        return new Result();
    }

    private boolean isTheArticle(Article article, List<String> subStrings){
        for(String subString : subStrings){
            if(!article.getText().contains(subString)){
                return false;
            }
        }
        return true;
    }

    private Map<String, List<ArticleReply>> getArticleReplyMap(List<ArticleReply> articleReplies){
        Map<String, List<ArticleReply>> map = new HashMap<>();
        articleReplies.forEach( reply -> {
            String articleId = reply.getArticleId();
            if(map.containsKey(articleId)){
                map.get(articleId).add(reply);
            }else {
                map.put(articleId, new ArrayList<>(Arrays.asList(reply)));
            }
        });
        return map;
    }

}
