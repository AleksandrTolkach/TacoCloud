package by.toukach.tacoclient.controller;

import by.toukach.tacoclient.dto.Ingredient;
import by.toukach.tacoclient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

  private final IngredientService ingredientService;

  @GetMapping
  public String ingredients(Model model) {
    Iterable<Ingredient> ingredients = ingredientService.findAll();
    model.addAttribute("ingredients", ingredients);
    return "ingredients";
  }

  @PostMapping
  public String addIngredient(Ingredient ingredient) {
    ingredientService.addIngredient(ingredient);
    return "home";
  }
}
