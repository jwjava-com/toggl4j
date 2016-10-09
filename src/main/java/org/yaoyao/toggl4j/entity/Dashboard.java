package org.yaoyao.toggl4j.entity;

import java.util.List;

import static com.sun.jmx.snmp.EnumRowStatus.active;

/**
 * @author Michal
 * @create 2016-10-09 22:07
 */
public class Dashboard {
  private List<ActiveUser> most_active_user;
  private List<Activity> activity;

  public Dashboard() {
  }

  public List<ActiveUser> getMost_active_user() {
    return most_active_user;
  }

  public void setMost_active_user(List<ActiveUser> most_active_user) {
    this.most_active_user = most_active_user;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  public void setActivity(List<Activity> activity) {
    this.activity = activity;
  }

  private static class ActiveUser {
    private long user_id;
    private long duration;

    public ActiveUser() {
    }

    public long getUser_id() {
      return user_id;
    }

    public void setUser_id(long user_id) {
      this.user_id = user_id;
    }

    public long getDuration() {
      return duration;
    }

    public void setDuration(long duration) {
      this.duration = duration;
    }

    @Override
    public String toString() {
      return "ActiveUser{" +
          "user_id=" + user_id +
          ", duration=" + duration +
          '}';
    }
  }


  private static class Activity {
    private long user_id;
    private long project_id;
    private long duration;
    private String description;

    public Activity() {
    }

    public long getUser_id() {
      return user_id;
    }

    public void setUser_id(long user_id) {
      this.user_id = user_id;
    }

    public long getProject_id() {
      return project_id;
    }

    public void setProject_id(long project_id) {
      this.project_id = project_id;
    }

    public long getDuration() {
      return duration;
    }

    public void setDuration(long duration) {
      this.duration = duration;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }


  }
}
