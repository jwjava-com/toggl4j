package org.yaoyao.toggl4j.entity;

public class Task {
  private String name;
  private long pid;
  private long wid;
  private long uid;
  private long estimated_seconds;
  private boolean active;
  private String at;
  private long done_seconds;

  public Task() {
  }

  public long getDone_seconds() {
    return done_seconds;
  }

  public void setDone_seconds(long done_seconds) {
    this.done_seconds = done_seconds;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getEstimated_seconds() {
    return estimated_seconds;
  }

  public void setEstimated_seconds(long estimated_seconds) {
    this.estimated_seconds = estimated_seconds;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  @Override
  public String toString() {
    return "Task{" +
        "name='" + name + '\'' +
        ", pid=" + pid +
        ", wid=" + wid +
        ", uid=" + uid +
        ", estimated_seconds=" + estimated_seconds +
        ", active=" + active +
        ", at='" + at + '\'' +
        ", done_seconds=" + done_seconds +
        '}';
  }
}
