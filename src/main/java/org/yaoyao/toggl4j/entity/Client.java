package org.yaoyao.toggl4j.entity;

import java.util.Objects;

public class Client {
  private long id;
  private long wid;
  private String name;
  private String at; // TODO: 2016/10/21 fixme type -> Instant or Java Time 
  private String notes;

  public Client() {
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
        Objects.equals(at, client.at) &&
        Objects.equals(notes, client.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, wid, name, at, notes);
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", wid=" + wid +
        ", name='" + name + '\'' +
        ", at='" + at + '\'' +
        ", notes='" + notes + '\'' +
        '}';
  }
}
