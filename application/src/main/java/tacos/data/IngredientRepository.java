package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {

  Mono<Ingredient> findBySlug(String slug);

  Flux<Ingredient> findAll();
}
