package tacos.messaging;

import jakarta.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.TacoOrder;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

  private JmsTemplate jms;
  private Destination destination;

  @Autowired
  public JmsOrderMessagingService(JmsTemplate jms, Destination destination) {
    this.jms = jms;
    this.destination = destination;
  }

  @Override
  public void sendOrder(TacoOrder order) {
    jms.convertAndSend(destination, order,
        message -> {
          message.setStringProperty("X_ORDER_SOURCE", "WEB");
          return message;
        });
  }
}
