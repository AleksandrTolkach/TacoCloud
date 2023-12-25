package kitchen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class ApplicationConfig {

  @Bean
  public StringJsonMessageConverter stringJsonMessageConverter() {
    return new StringJsonMessageConverter();
  }
}
