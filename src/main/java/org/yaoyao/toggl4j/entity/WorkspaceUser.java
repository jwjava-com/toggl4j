package org.yaoyao.toggl4j.entity;

/**
 * @author Michal
 * @create 2016-10-10 20:45
 */
public class WorkspaceUser {
  private long id;
  private long uid;
  private boolean admin;
  private boolean active;
  private String invite_url;

  public WorkspaceUser() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getInvite_url() {
    return invite_url;
  }

  public void setInvite_url(String invite_url) {
    this.invite_url = invite_url;
  }

  @Override
  public String toString() {
    return "WorkspaceUser{" +
        "id=" + id +
        ", uid=" + uid +
        ", admin=" + admin +
        ", active=" + active +
        ", invite_url='" + invite_url + '\'' +
        '}';
  }
}
