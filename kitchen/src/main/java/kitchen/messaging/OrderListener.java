package kitchen.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import kitchen.TacoOrder;

@Slf4j
@Component
public class OrderListener {

  @KafkaListener(topics = "tacocloud.orders.topic", groupId = "taco")
  public void handle(TacoOrder tacoOrder) {
    log.info("{}", tacoOrder);
  }
}
