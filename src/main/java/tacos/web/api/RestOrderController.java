package tacos.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.TacoOrder;
import tacos.User;
import tacos.data.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class RestOrderController {

  private final OrderRepository orderRepository;

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Iterable<TacoOrder> getAll() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("placedAt").descending());
    return orderRepository.findAllBy(page).getContent();
  }

  @PutMapping(value = "/{orderId}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public TacoOrder putOrder(@PathVariable Long orderId,
      @RequestBody TacoOrder order,
      @AuthenticationPrincipal User user) {

    order.setId(orderId);
    order.setUser(user);
    return orderRepository.save(order);
  }

  @PatchMapping(value = "/{orderId}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public TacoOrder patchOrder(@PathVariable Long orderId,
      @RequestBody TacoOrder patch,
      @AuthenticationPrincipal User user) {
    TacoOrder order = orderRepository.findById(orderId).get();
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {
      order.setDeliveryZip(patch.getDeliveryZip());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    order.setUser(user);
    return orderRepository.save(order);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable Long orderId) {
    try {
      orderRepository.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {
    }
  }
}
