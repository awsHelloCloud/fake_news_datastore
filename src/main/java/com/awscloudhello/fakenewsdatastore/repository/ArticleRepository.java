package com.awscloudhello.fakenewsdatastore.repository;

import com.awscloudhello.fakenewsdatastore.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findTopByTextLike(String text);
}
