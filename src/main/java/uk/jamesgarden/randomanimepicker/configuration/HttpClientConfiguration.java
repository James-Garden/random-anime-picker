package uk.jamesgarden.randomanimepicker.configuration;

import java.net.http.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

  private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

  @Bean
  public HttpClient httpClient() {
    return HTTP_CLIENT;
  }
}
