package com.codestates.model.user;

public class UserDto {
  private int id;
  private String name;
  private String email;
  private Integer roleId;

  public UserDto(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.roleId = null;
  }

  public UserDto(int id, String name, String email, int roleId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.roleId = roleId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public Integer getRoleId() {
    return roleId;
  }

}
