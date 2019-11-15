package com.awscloudhello.fakenewsdatastore.repository;

import com.awscloudhello.fakenewsdatastore.model.ArticleReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleReplyRepository extends JpaRepository<ArticleReply, Long> {

    List<ArticleReply> findAllByArticleIdIn(List<String> articleIdList);
}
