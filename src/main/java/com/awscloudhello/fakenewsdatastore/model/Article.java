package com.awscloudhello.fakenewsdatastore.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Article {
    @Id
    @Column(unique = true)
    private String id;
    @Column(name ="ref")
    private String references;
    private String userIdsha;
    private String appId;
    private String tags;
    private Integer normalArticleReplyCount;
    @Lob
    private String text;
    private String hyperlinks;
    private String createdAt;
    private String updatedAt;
    private String lastRequestedAt;

}
