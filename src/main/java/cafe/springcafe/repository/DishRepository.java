package cafe.springcafe.repository;

import cafe.springcafe.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

   public Dish getDishByName(String name);
   
}
