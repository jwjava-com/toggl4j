package org.yaoyao.toggl4j.entity;

import static javafx.scene.input.KeyCode.R;

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

  public Project() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }

  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean is_private() {
    return is_private;
  }

  public void setIs_private(boolean is_private) {
    this.is_private = is_private;
  }

  public boolean isTemplate() {
    return template;
  }

  public void setTemplate(boolean template) {
    this.template = template;
  }

  public long getTemplate_id() {
    return template_id;
  }

  public void setTemplate_id(long template_id) {
    this.template_id = template_id;
  }

  public boolean isBillable() {
    return billable;
  }

  public void setBillable(boolean billable) {
    this.billable = billable;
  }

  public boolean isAuto_estimates() {
    return auto_estimates;
  }

  public void setAuto_estimates(boolean auto_estimates) {
    this.auto_estimates = auto_estimates;
  }

  public int getEstimated_hours() {
    return estimated_hours;
  }

  public void setEstimated_hours(int estimated_hours) {
    this.estimated_hours = estimated_hours;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "Project{" +
        "name='" + name + '\'' +
        ", wid=" + wid +
        ", cid=" + cid +
        ", active=" + active +
        ", is_private=" + is_private +
        ", template=" + template +
        ", template_id=" + template_id +
        ", billable=" + billable +
        ", auto_estimates=" + auto_estimates +
        ", estimated_hours=" + estimated_hours +
        ", at='" + at + '\'' +
        ", color='" + color + '\'' +
        ", rate=" + rate +
        '}';
  }
}
