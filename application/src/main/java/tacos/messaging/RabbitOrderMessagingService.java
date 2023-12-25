package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tacos.TacoOrder;

@Service
@RequiredArgsConstructor
public class RabbitOrderMessagingService implements OrderMessagingService {

  private final RabbitTemplate rabbit;

  @Override
  public void sendOrder(TacoOrder order) {
    rabbit.convertAndSend("tacocloud.order", order);
  }
}
