package cafe.springcafe.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int price;

    private int estimatedCookingTime;

    @ManyToMany
    @JoinTable(
        name = "dish_ingredients",
        joinColumns = @JoinColumn(name = "dish_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    protected Dish() {}

    public Dish(String name, String description, int estimatedCookingTime, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.estimatedCookingTime = estimatedCookingTime;
        this.ingredients = ingredients;
        this.price = (int) calcPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getEstimatedCookingTime() {
        return estimatedCookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    private float calcPrice() {
        float intermediaryPrice = 0;
        for (Ingredient ingredient : ingredients) {
            intermediaryPrice += ingredient.getPrice();
        }
        return intermediaryPrice * 1.2f;
    }

    @Override
    public String toString() {
        StringBuilder menuItem = new StringBuilder(name + "\n" + description + "\n");
        for (Ingredient ingredient : ingredients) {
            menuItem.append(ingredient.getName()).append(", ");
        }
        menuItem.append("\nPrice: ").append(calcPrice()).append("\n");
        return menuItem.toString();
    }
}