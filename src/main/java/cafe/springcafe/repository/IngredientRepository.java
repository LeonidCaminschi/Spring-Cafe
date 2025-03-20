package cafe.springcafe.repository;

import cafe.springcafe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findByName(String name);
}
