package org.yaoyao.toggl4j.entity;

public class TimeEntry {
  // TODO: 2016/10/10 at type? -> string or time?
  private String description;
  private long wid;
  private long pid;
  private long tid;
  private boolean billable;
  private String start;
  private String stop;
  private long duration;
  private String created_with;
  private boolean duronly;
  private String at;

  public TimeEntry() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

  public long getTid() {
    return tid;
  }

  public void setTid(long tid) {
    this.tid = tid;
  }

  public boolean isBillable() {
    return billable;
  }

  public void setBillable(boolean billable) {
    this.billable = billable;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getStop() {
    return stop;
  }

  public void setStop(String stop) {
    this.stop = stop;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public String getCreated_with() {
    return created_with;
  }

  public void setCreated_with(String created_with) {
    this.created_with = created_with;
  }

  public boolean isDuronly() {
    return duronly;
  }

  public void setDuronly(boolean duronly) {
    this.duronly = duronly;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  @Override
  public String toString() {
    return "TimeEntry{" +
        "description='" + description + '\'' +
        ", wid=" + wid +
        ", pid=" + pid +
        ", tid=" + tid +
        ", billable=" + billable +
        ", start='" + start + '\'' +
        ", stop='" + stop + '\'' +
        ", duration=" + duration +
        ", created_with='" + created_with + '\'' +
        ", duronly=" + duronly +
        ", at='" + at + '\'' +
        '}';
  }
}
