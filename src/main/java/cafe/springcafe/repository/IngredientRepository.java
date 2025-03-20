package cafe.springcafe.repository;

import cafe.springcafe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
   List<Ingredient> findByName(String name);
}
