package org.yaoyao.toggl4j.entity;

import java.util.Objects;

public class Blog {
  private String title;
  private String url;

  public Blog() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Blog blog = (Blog) o;
    return Objects.equals(title, blog.title) && Objects.equals(url, blog.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, url);
  }

  @Override
  public String toString() {
    return "Blog{" +
        "title='" + title + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
