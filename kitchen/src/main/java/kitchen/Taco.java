package kitchen;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Taco {

  private Long id;
  private Date createdAt;
  private String name;
  private List<Ingredient> ingredients;
  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }
}
