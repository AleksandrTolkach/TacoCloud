package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;

@RestController
@RequestMapping(path = "/api/orders",
    produces = "application/json")
@CrossOrigin
public class OrderApiController {

  private OrderRepository orderRepository;
  private OrderMessagingService messagingService;

  public OrderApiController(OrderRepository orderRepository,
      OrderMessagingService messagingService) {
    this.orderRepository = orderRepository;
    this.messagingService = messagingService;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TacoOrder postOrder(@RequestBody TacoOrder order) {
    messagingService.sendOrder(order);
    return orderRepository.save(order);
  }
}
