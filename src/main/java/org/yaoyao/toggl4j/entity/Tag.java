package org.yaoyao.toggl4j.entity;

public class Tag {
  private long id;
  private long wid;
  private String name;

  public Tag() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Tag{" +
        "id=" + id +
        ", wid=" + wid +
        ", name='" + name + '\'' +
        '}';
  }
}
