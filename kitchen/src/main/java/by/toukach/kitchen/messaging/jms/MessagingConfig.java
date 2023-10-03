package by.toukach.kitchen.messaging.jms;

import by.toukach.kitchen.TacoOrder;
import jakarta.jms.Destination;
import java.util.HashMap;
import java.util.Map;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
public class MessagingConfig {

  @Bean
  public MessageConverter messageConverter() {
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setTypeIdPropertyName("_typeId");

    Map<String, Class<?>> typeIdMapping = new HashMap<>();
    typeIdMapping.put("order", TacoOrder.class);

    messageConverter.setTypeIdMappings(typeIdMapping);

    return messageConverter;
  }

  @Bean
  public Destination destination() {
    return new ActiveMQQueue("tacocloud.order.queue");
  }
}
