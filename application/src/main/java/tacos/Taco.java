package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@NoArgsConstructor
@RestResource(rel = "tacos", path = "tacos")
public class Taco {

  @Id
  private Long id;

  @NonNull
  private String name;

  private Set<Long> ingredients = new HashSet<>();

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient.getId());
  }
}
