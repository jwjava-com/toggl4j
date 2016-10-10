package org.yaoyao.toggl4j.entity;

/**
 * @author Michal
 * @create 2016-10-10 20:46
 */
public class Workspace {
  private String name;
  private boolean premium;
  private boolean admin;
  private double default_hourly_rate;
  private String default_currency;
  private boolean only_admins_may_create_projects;
  private boolean only_admins_see_billable_rates;
  private long rounding;
  private int rounding_minutes;
  private String at;
  private String logo_url;

  public Workspace() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPremium() {
    return premium;
  }

  public void setPremium(boolean premium) {
    this.premium = premium;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public double getDefault_hourly_rate() {
    return default_hourly_rate;
  }

  public void setDefault_hourly_rate(double default_hourly_rate) {
    this.default_hourly_rate = default_hourly_rate;
  }

  public String getDefault_currency() {
    return default_currency;
  }

  public void setDefault_currency(String default_currency) {
    this.default_currency = default_currency;
  }

  public boolean isOnly_admins_may_create_projects() {
    return only_admins_may_create_projects;
  }

  public void setOnly_admins_may_create_projects(boolean only_admins_may_create_projects) {
    this.only_admins_may_create_projects = only_admins_may_create_projects;
  }

  public boolean isOnly_admins_see_billable_rates() {
    return only_admins_see_billable_rates;
  }

  public void setOnly_admins_see_billable_rates(boolean only_admins_see_billable_rates) {
    this.only_admins_see_billable_rates = only_admins_see_billable_rates;
  }

  public long getRounding() {
    return rounding;
  }

  public void setRounding(long rounding) {
    this.rounding = rounding;
  }

  public int getRounding_minutes() {
    return rounding_minutes;
  }

  public void setRounding_minutes(int rounding_minutes) {
    this.rounding_minutes = rounding_minutes;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  public String getLogo_url() {
    return logo_url;
  }

  public void setLogo_url(String logo_url) {
    this.logo_url = logo_url;
  }

  @Override
  public String toString() {
    return "Workspace{" +
        "name='" + name + '\'' +
        ", premium=" + premium +
        ", admin=" + admin +
        ", default_hourly_rate=" + default_hourly_rate +
        ", default_currency='" + default_currency + '\'' +
        ", only_admins_may_create_projects=" + only_admins_may_create_projects +
        ", only_admins_see_billable_rates=" + only_admins_see_billable_rates +
        ", rounding=" + rounding +
        ", rounding_minutes=" + rounding_minutes +
        ", at='" + at + '\'' +
        ", logo_url='" + logo_url + '\'' +
        '}';
  }
}
