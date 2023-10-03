package by.toukach.kitchen.messaging.jms;

import by.toukach.kitchen.TacoOrder;
import jakarta.jms.JMSException;

public interface OrderReceiver {

  TacoOrder receiveOrder() throws JMSException;
}
