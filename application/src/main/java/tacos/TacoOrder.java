package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  private String deliveryName;

  private String deliveryStreet;

  private String deliveryCity;

  private String deliveryState;

  private String deliveryZip;

  private String ccNumber;

  private String ccExpiration;

  private String ccCVV;

  private Set<Long> tacoIds =  new LinkedHashSet<>();

  public void addTaco(Taco taco) {
    this.tacoIds.add(taco.getId());
  }
}
