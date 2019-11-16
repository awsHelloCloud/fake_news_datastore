package com.awscloudhello.fakenewsdatastore.model;

public class ArticleOM {

    public static Article newArticle(){
        return new Article();
    }

    public static Article newArticle(String text){
        Article article = new Article();
        article.setText(text);
        return article;
    }
}
