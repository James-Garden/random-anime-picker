package uk.jamesgarden.randomanimepicker.maluser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "mal_users")
public class MalUser {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private String username;
  
  private ZonedDateTime lastUpdated;

  public MalUser() {
  }

  MalUser(String username) {
    this.username = username;
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public ZonedDateTime getLastUpdated() {
    return lastUpdated;
  }

  void setLastUpdated(ZonedDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}
