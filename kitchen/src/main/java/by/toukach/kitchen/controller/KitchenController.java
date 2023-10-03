package by.toukach.kitchen.controller;

import by.toukach.kitchen.TacoOrder;
import by.toukach.kitchen.messaging.jms.OrderReceiver;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
public class KitchenController {

  private final OrderReceiver orderReceiver;

  @GetMapping("/orders")
  public TacoOrder findOrder() throws JMSException {
    return orderReceiver.receiveOrder();
  }
}
