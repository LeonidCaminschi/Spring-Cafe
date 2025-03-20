package cafe.springcafe.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cook_id", nullable = false)
    private Cook cook;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public Order() {
    }

    public Order(Cook cook, Dish dish) {
        this.cook = cook;
        this.dish = dish;
    }

    public Long getId() {
        return id;
    }

    public Cook getCook() {
        return cook;
    }

    public Dish getDish() {
        return dish;
    }
}