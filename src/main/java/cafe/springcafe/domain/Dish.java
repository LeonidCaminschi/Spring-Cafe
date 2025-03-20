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

    public Dish() {
    }

    public Dish(String name, String description, int estimatedCookingTime, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.estimatedCookingTime = estimatedCookingTime;
        this.ingredients = ingredients;
        this.price = (int) calcPrice(); // Calculate price based on ingredients
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEstimatedCookingTime() {
        return estimatedCookingTime;
    }

    public void setEstimatedCookingTime(int estimatedCookingTime) {
        this.estimatedCookingTime = estimatedCookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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