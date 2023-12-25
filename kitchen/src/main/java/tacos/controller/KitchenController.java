package tacos.controller;

import tacos.TacoOrder;
import tacos.messaging.OrderReceiver;
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
  public TacoOrder findOrder() {
    return orderReceiver.receiveOrder();
  }

  public void listen(TacoOrder order) {
    System.out.println(order);
  }
}
