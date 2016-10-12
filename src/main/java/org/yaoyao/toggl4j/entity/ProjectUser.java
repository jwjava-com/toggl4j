package org.yaoyao.toggl4j.entity;

public class ProjectUser {
  private long id;
  private long pid;
  private long uid;
  private long wid;
  private boolean manager;
  private int rate;
  private String fullname;
  private String at;

  public ProjectUser() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public boolean isManager() {
    return manager;
  }

  public void setManager(boolean manager) {
    this.manager = manager;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  @Override
  public String toString() {
    return "ProjectUser{" +
        "id=" + id +
        ", pid=" + pid +
        ", uid=" + uid +
        ", wid=" + wid +
        ", manager=" + manager +
        ", rate=" + rate +
        ", fullname='" + fullname + '\'' +
        ", at='" + at + '\'' +
        '}';
  }
}
