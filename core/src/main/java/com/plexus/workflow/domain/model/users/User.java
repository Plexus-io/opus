package com.plexus.workflow.domain.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  private UUID userId;

  //  @Column(name = "user_name")
  //  private String username;

  @Column(name = "email")
  private String email;

  //    Check below Date variables format if they are the same for timeStamp in psql

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "last_activity")
  private Date lastActivity;

  @Column(name = "img_url")
  private String imgUrl;

  public UUID getUserId() {
    return userId;
  }

  //  TODO
  //  public String getUsername() {
  //    return username;
  //  }

  public String getEmail() {
    return email;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getLastActivity() {
    return lastActivity;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  //  public void setUsername(String username) {
  //    this.username = username;
  //  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setLastActivity(Date lastActivity) {
    this.lastActivity = lastActivity;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
