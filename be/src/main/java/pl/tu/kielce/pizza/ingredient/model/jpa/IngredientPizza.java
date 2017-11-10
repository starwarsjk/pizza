package pl.tu.kielce.pizza.ingredient.model.jpa;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.pizza.model.jpa.Pizza;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INGREDIENT_PIZZA")
public class IngredientPizza extends Ingredient{

    @ManyToOne
    private Pizza pizza;

//    @Builder
//    public IngredientPizza(Item item, Double quantity, Pizza pizza) {
//        super(item, quantity);
//        this.pizza = pizza;
//    }
//
//    public static class IngredientPizzaBuilder extends Ingredient.IngredientBuilder {
//        IngredientPizzaBuilder() {
//            super();
//        }
//
//    }
}
