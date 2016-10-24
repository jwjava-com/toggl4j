package org.yaoyao.toggl4j.entity;

import java.util.Objects;

public class Client {
  private long id;
  private long wid;
  private String name;
  private String at; // TODO: 2016/10/21 fixme type -> Instant or Java Time 

  public Client(long id, long wid, String name, String at) {
    this.id = id;
    this.wid = wid;
    this.name = name;
    this.at = at;
  }

  public Client() {
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

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Client client = (Client) o;
    return id == client.id &&
        wid == client.wid &&
        Objects.equals(name, client.name) &&
        Objects.equals(at, client.at);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, wid, name, at);
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", wid=" + wid +
        ", name='" + name + '\'' +
        ", at='" + at + '\'' +
        '}';
  }
}
