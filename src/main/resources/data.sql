DELETE FROM Ingredient_Ref;
DELETE FROM Taco;
DELETE FROM Taco_Order;

DELETE FROM Ingredient;
INSERT INTO Ingredient (id, name, type)
    VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('GRBF', 'Croung Beef', 'PROTEIN');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO Ingredient (id, name, Type)
    VALUES ('SRCR', 'Sour Cream', 'SAUCE');
