package cafe.springcafe.repository;

import cafe.springcafe.domain.Dish;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

//   @EntityGraph(attributePaths = {
//      "ingredients"
//   })
   public Dish getDishByName(String name);
   
}
