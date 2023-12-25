package tacos.messaging;

import tacos.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SmartMessageConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitOrderReceiver implements OrderReceiver {

  private final RabbitTemplate rabbit;
  private final SmartMessageConverter converter;

  @Override
  public TacoOrder receiveOrder() {
    Message message = rabbit.receive("tacocloud.order");
    return message != null
        ? (TacoOrder) converter.fromMessage(message)
        : null;
  }
}
