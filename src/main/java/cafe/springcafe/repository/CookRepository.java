package cafe.springcafe.repository;

import cafe.springcafe.domain.Cook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CookRepository extends CrudRepository<Cook, Long> {
    List<Cook> findByLeastOrders();
}
