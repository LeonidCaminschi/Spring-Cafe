package cafe.springcafe.repository;

import cafe.springcafe.domain.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> findByName(String name);
}
