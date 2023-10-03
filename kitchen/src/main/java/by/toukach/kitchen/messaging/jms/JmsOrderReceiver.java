package by.toukach.kitchen.messaging.jms;

import by.toukach.kitchen.TacoOrder;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jms;

  @Autowired
  public JmsOrderReceiver(JmsTemplate jms) {
    this.jms = jms;
  }

  @Override
  public TacoOrder receiveOrder() throws JMSException {
    return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
  }
}
