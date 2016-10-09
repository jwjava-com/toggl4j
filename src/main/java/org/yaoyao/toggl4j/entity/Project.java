package org.yaoyao.toggl4j.entity;

import static javafx.scene.input.KeyCode.R;

/**
 * @author Michal
 * @create 2016-10-09 22:17
 */
public class Project {
  private String name;
  private long wid;
  private long cid;
  private boolean active;
  private boolean is_private;
  private boolean template;
  private long template_id;
  private boolean billable;
  private boolean auto_estimates;
  private int estimated_hours;
  private String at;
  private String color;
  private double rate;
}
