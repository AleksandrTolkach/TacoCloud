package by.toukach.client.service;

import by.toukach.client.dto.Ingredient;

public interface IngredientService {

  Iterable<Ingredient> findAll();

  Ingredient addIngredient(Ingredient ingredient);
}
