package cafe.springcafe.repository;

import cafe.springcafe.domain.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookRepository extends JpaRepository<Cook, Long> {

    public Cook getCookById(Long id);

}
