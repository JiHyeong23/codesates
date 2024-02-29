package com.codestates.model.content;

import java.sql.Timestamp;

public class ContentDto {
  private int id;
  private String title;
  private String body;
  private Timestamp timestamp;
  private Integer userId;

  public ContentDto(int id, String title, String body, Timestamp timestamp) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.timestamp = timestamp;
    this.userId = null;
  }

  public ContentDto(int id, String title, String body, Timestamp timestamp, int userId) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.timestamp = timestamp;
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Integer getUserId() {
    return userId;
  }

}
