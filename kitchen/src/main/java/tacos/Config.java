package tacos;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SmartMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public SmartMessageConverter smartMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
