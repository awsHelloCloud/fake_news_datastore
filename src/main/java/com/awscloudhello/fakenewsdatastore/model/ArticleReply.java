package com.awscloudhello.fakenewsdatastore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ArticleReply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String articleId;
    private String replyId;
    private String userId;
    private Integer negativeFeedbackCount;
    private Integer positiveFeedbackCount;
    private String replyType;
    private String appId;
    private String status;
    private String createdAt;
    private String updatedAt;

}
