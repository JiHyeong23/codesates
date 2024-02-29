package com.codestates.model.role;

public class RoleDto {
  private int id;
  private String name;

  public RoleDto(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
