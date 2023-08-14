package tacos.data;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {
  List<Ingredient> findAll();
}
