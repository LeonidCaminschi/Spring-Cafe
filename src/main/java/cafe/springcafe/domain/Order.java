package cafe.springcafe.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="orders")
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

    public Order() {}

    public Order(Cook cook, Dish dish) {
        this.cook = cook;
        this.dish = dish;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id) && Objects.equals(cook, order.cook) && Objects.equals(dish, order.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cook, dish);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cook=" + cook +
                ", dish=" + dish +
                '}';
    }
}