package by.toukach.client.controller;

import by.toukach.client.dto.Ingredient;
import by.toukach.client.service.IngredientService;
import by.toukach.client.service.RestIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/ingredients")
@RequiredArgsConstructor
public class ManageIngredientsController {

  @GetMapping
  public String ingredientsAdmin(Model model) {
    IngredientService ingredientService = new RestIngredientService("");
    model.addAttribute("ingredients", ingredientService.findAll());
    return "ingredientsAdmin";
  }

  @PostMapping
  public String addIngredient(Ingredient ingredient) {
    IngredientService ingredientService = new RestIngredientService("");
    ingredientService.addIngredient(ingredient);
    return "redirect:/admin/ingredients";
  }
}
