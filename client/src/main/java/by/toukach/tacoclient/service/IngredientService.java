package by.toukach.tacoclient.service;

import by.toukach.tacoclient.dto.Ingredient;

public interface IngredientService {

  Iterable<Ingredient> findAll();

  Ingredient addIngredient(Ingredient ingredient);
}
