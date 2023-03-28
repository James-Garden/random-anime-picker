package uk.jamesgarden.randomanimepicker.welcome;

import java.util.Objects;

public class WelcomeForm {

  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    if (Objects.nonNull(username)) {
      this.username = username.strip();
      return;
    }
    this.username = null;
  }
}
