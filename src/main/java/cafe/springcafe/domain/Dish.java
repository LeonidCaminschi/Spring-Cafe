package cafe.springcafe.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "estimated_cooking_time")
    private Integer estimatedCookingTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "dish_ingredients",
        joinColumns = @JoinColumn(name = "dish_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Dish() {}

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

    public Integer getPrice() {
        return price;
    }

    public Integer getEstimatedCookingTime() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setEstimatedCookingTime(int estimatedCookingTime) {
        this.estimatedCookingTime = estimatedCookingTime;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dish dish)) return false;
        return price == dish.price && estimatedCookingTime == dish.estimatedCookingTime && Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && Objects.equals(ingredients, dish.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, estimatedCookingTime, ingredients);
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