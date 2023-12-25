package tacos;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SmartMessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@Configuration
public class Config {

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo,
      PasswordEncoder encoder, TacoRepository tacoRepo) {
    return args -> {
      Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
      Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
      Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
      Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
      Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
      Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
      Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
      Ingredient monterreyJack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
      Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
      Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
      repo.save(flourTortilla);
      repo.save(cornTortilla);
      repo.save(groundBeef);
      repo.save(carnitas);
      repo.save(dicedTomatoes);
      repo.save(lettuce);
      repo.save(cheddar);
      repo.save(monterreyJack);
      repo.save(salsa);
      repo.save(sourCream);
      log.info("Ingredients were successfully added.");

      Taco taco1 = new Taco();
      taco1.setName("Carnivore");
      taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas,
          sourCream, salsa, cheddar));
      tacoRepo.save(taco1);

      Taco taco2 = new Taco();
      taco2.setName("Bovine Bounty");
      taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar,
          monterreyJack, sourCream));
      tacoRepo.save(taco2);

      Taco taco3 = new Taco();
      taco3.setName("Veg-out");
      taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, dicedTomatoes,
          lettuce, salsa));
      tacoRepo.save(taco3);
    };
  }

  @Bean
  public SmartMessageConverter smartMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
