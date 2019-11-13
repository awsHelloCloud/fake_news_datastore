package com.awscloudhello.fakenewsdatastore.repository;

import com.awscloudhello.fakenewsdatastore.model.ArticleReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleReplyRepository extends JpaRepository<ArticleReply, Long> {
    ArticleReply findByArticleId(String articleId);
}
