package com.awscloudhello.fakenewsdatastore.repository;

import com.awscloudhello.fakenewsdatastore.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTextLike(String text);
}
