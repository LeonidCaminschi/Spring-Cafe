package cafe.springcafe.repository;

import cafe.springcafe.domain.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
   List<Dish> findByName(String name);
}
