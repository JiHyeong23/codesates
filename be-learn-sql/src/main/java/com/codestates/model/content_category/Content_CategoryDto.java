package com.codestates.model.content_category;

public class Content_CategoryDto {
  private int id;
  private int contentId;
  private int categoryId;

  public Content_CategoryDto(int id, int contentId, int categoryId) {
    this.id = id;
    this.contentId = contentId;
    this.categoryId = categoryId;
  }

  public int getId() {
    return id;
  }

  public int getContentId() {
    return contentId;
  }

  public int getCategoryId() {
    return categoryId;
  }
}
