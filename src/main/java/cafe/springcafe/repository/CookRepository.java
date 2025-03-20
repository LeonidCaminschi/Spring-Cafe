package cafe.springcafe.repository;

import cafe.springcafe.domain.Cook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookRepository extends CrudRepository<Cook, Long> {
    List<Cook> findByName(String name);
}
