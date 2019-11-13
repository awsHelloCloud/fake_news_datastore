package com.awscloudhello.fakenewsdatastore.service;

import com.awscloudhello.fakenewsdatastore.model.Article;
import com.awscloudhello.fakenewsdatastore.model.ArticleReply;
import com.awscloudhello.fakenewsdatastore.repository.ArticleReplyRepository;
import com.awscloudhello.fakenewsdatastore.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean isFakeNews(String text){
        Optional<Article> optionalArticle = articleRepository.findTopByTextLike(text);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            ArticleReply articleReply = articleReplyRepository.findByArticleId(article.getId());
            return RUMOR.equals(articleReply.getReplyType());
        }
        return false;
    }
}
