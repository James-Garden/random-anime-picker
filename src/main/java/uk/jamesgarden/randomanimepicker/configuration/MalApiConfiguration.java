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

  /**
   * The base hostname for the MyAnimeList API
   */
  private String hostname;

  /**
   * The path to the MyAnimeList API from the root (hostname)
   */
  private String basePath;

  /**
   * The path to get a user's animelist, with a single %s which will be formatted with their username
   */
  private String animelistPath;

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

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getBasePath() {
    return basePath;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getAnimelistPath() {
    return animelistPath;
  }

  public void setAnimelistPath(String animelistPath) {
    this.animelistPath = animelistPath;
  }
}
