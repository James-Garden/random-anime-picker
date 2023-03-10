package uk.jamesgarden.randomanimepicker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mal-api")
public class MalApiConfiguration {

  /**
   * The secret client ID provided when creating an API on MyAnimeList, sent with API requests
   */
  private String clientId;

  /**
   * The client secret provided when creating an API on MyAnimeList
   */
  private String clientSecret;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }
}
