package cafe.springcafe.repository;

import cafe.springcafe.dto.CookOrderCount;
import cafe.springcafe.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = """
                    SELECT c.id AS cook_id, 
                                COUNT(o.id) AS order_count
                    FROM cooks c
                    LEFT JOIN orders o ON c.id = o.cook_id
                    GROUP BY c.id
                    ORDER BY cook_id;
                    """,
            nativeQuery = true
    )
    public List<CookOrderCount> getCookOrderCounts();

    //TODO: try to make the query not by hand using Jpa
    //TODO: research what is better to make a bigger select or process data from inside java
}