package by.toukach.kitchen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private Date placedAt;
  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;
  private String ccNumber;
  private String ccExpiration;
  private String ccCVV;
  private List<Taco> tacos =  new ArrayList<>();
  private User user;

  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }
}
