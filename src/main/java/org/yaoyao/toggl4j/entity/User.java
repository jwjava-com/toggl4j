package org.yaoyao.toggl4j.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author Michal
 * @create 2016-10-09 21:46
 */
public class User {
  private String api_token;
  private int default_wid;
  private String email;
  private String jquery_timeofday_format;
  private String jquery_date_format;
  private String timeofday_format;
  private String date_format;
  private boolean store_start_and_stop_time;
  private int beginning_of_week;
  private String language;
  private String image_url;
  private boolean sidebar_piechart;
  private String at;
  private Blog new_blog_post;
  private boolean send_product_emails;
  private boolean send_weekly_report;
  private boolean openid_enabled;
  private String timezone;

  public User() {
  }

  public String getApi_token() {
    return api_token;
  }

  public void setApi_token(String api_token) {
    this.api_token = api_token;
  }

  public int getDefault_wid() {
    return default_wid;
  }

  public void setDefault_wid(int default_wid) {
    this.default_wid = default_wid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getJquery_timeofday_format() {
    return jquery_timeofday_format;
  }

  public void setJquery_timeofday_format(String jquery_timeofday_format) {
    this.jquery_timeofday_format = jquery_timeofday_format;
  }

  public String getJquery_date_format() {
    return jquery_date_format;
  }

  public void setJquery_date_format(String jquery_date_format) {
    this.jquery_date_format = jquery_date_format;
  }

  public String getTimeofday_format() {
    return timeofday_format;
  }

  public void setTimeofday_format(String timeofday_format) {
    this.timeofday_format = timeofday_format;
  }

  public String getDate_format() {
    return date_format;
  }

  public void setDate_format(String date_format) {
    this.date_format = date_format;
  }

  public boolean isStore_start_and_stop_time() {
    return store_start_and_stop_time;
  }

  public void setStore_start_and_stop_time(boolean store_start_and_stop_time) {
    this.store_start_and_stop_time = store_start_and_stop_time;
  }

  public int getBeginning_of_week() {
    return beginning_of_week;
  }

  public void setBeginning_of_week(int beginning_of_week) {
    this.beginning_of_week = beginning_of_week;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public boolean isSidebar_piechart() {
    return sidebar_piechart;
  }

  public void setSidebar_piechart(boolean sidebar_piechart) {
    this.sidebar_piechart = sidebar_piechart;
  }

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  public Blog getNew_blog_post() {
    return new_blog_post;
  }

  public void setNew_blog_post(Blog new_blog_post) {
    this.new_blog_post = new_blog_post;
  }

  public boolean isSend_product_emails() {
    return send_product_emails;
  }

  public void setSend_product_emails(boolean send_product_emails) {
    this.send_product_emails = send_product_emails;
  }

  public boolean isSend_weekly_report() {
    return send_weekly_report;
  }

  public void setSend_weekly_report(boolean send_weekly_report) {
    this.send_weekly_report = send_weekly_report;
  }

  public boolean isOpenid_enabled() {
    return openid_enabled;
  }

  public void setOpenid_enabled(boolean openid_enabled) {
    this.openid_enabled = openid_enabled;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return default_wid == user.default_wid &&
        store_start_and_stop_time == user.store_start_and_stop_time &&
        beginning_of_week == user.beginning_of_week &&
        sidebar_piechart == user.sidebar_piechart &&
        at == user.at &&
        send_product_emails == user.send_product_emails &&
        send_weekly_report == user.send_weekly_report &&
        openid_enabled == user.openid_enabled &&
        Objects.equals(api_token, user.api_token) &&
        Objects.equals(email, user.email) &&
        Objects.equals(jquery_timeofday_format, user.jquery_timeofday_format) &&
        Objects.equals(jquery_date_format, user.jquery_date_format) &&
        Objects.equals(timeofday_format, user.timeofday_format) &&
        Objects.equals(date_format, user.date_format) &&
        Objects.equals(language, user.language) &&
        Objects.equals(image_url, user.image_url) &&
        Objects.equals(new_blog_post, user.new_blog_post) &&
        Objects.equals(timezone, user.timezone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(api_token, default_wid, email, jquery_timeofday_format, jquery_date_format, timeofday_format, date_format, store_start_and_stop_time, beginning_of_week, language, image_url, sidebar_piechart, at, new_blog_post, send_product_emails, send_weekly_report, openid_enabled, timezone);
  }

  @Override
  public String toString() {
    return "User{" +
        "api_token='" + api_token + '\'' +
        ", default_wid=" + default_wid +
        ", email='" + email + '\'' +
        ", jquery_timeofday_format='" + jquery_timeofday_format + '\'' +
        ", jquery_date_format='" + jquery_date_format + '\'' +
        ", timeofday_format='" + timeofday_format + '\'' +
        ", date_format='" + date_format + '\'' +
        ", store_start_and_stop_time=" + store_start_and_stop_time +
        ", beginning_of_week=" + beginning_of_week +
        ", language='" + language + '\'' +
        ", image_url='" + image_url + '\'' +
        ", sidebar_piechart=" + sidebar_piechart +
        ", at=" + at +
        ", new_blog_post=" + new_blog_post +
        ", send_product_emails=" + send_product_emails +
        ", send_weekly_report=" + send_weekly_report +
        ", openid_enabled=" + openid_enabled +
        ", timezone='" + timezone + '\'' +
        '}';
  }
}
