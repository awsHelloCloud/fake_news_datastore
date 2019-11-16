package com.awscloudhello.fakenewsdatastore.model;

public class ArticleReplyOM {

    public static ArticleReply newArticleReply(String replayType){
        ArticleReply reply = new ArticleReply();
        reply.setReplyType(replayType);
        return reply;
    }
}
