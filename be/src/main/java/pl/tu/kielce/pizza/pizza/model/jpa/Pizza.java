package pl.tu.kielce.pizza.pizza.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.department.model.jpa.Department;
import pl.tu.kielce.pizza.ingredient.model.jpa.IngredientPizza;
import pl.tu.kielce.pizza.ingredient.model.jpa.PizzaSize;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PIZZA")
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizza")
    private List<IngredientPizza> ingredientDepartments;

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    private PizzaSize pizzaSize;

    @ManyToOne
    private Department department;
}
