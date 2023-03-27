package uk.jamesgarden.randomanimepicker.configuration;

import java.time.Clock;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfiguration {

  private static final Clock CLOCK = Clock.system(ZoneId.of("Europe/London"));

  @Bean
  public Clock clock() {
    return CLOCK;
  }
}
