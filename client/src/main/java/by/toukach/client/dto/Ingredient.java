package by.toukach.client.dto;

import lombok.Data;

@Data
public class Ingredient {

  private String id;
  private String name;
  private Type type;

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}
