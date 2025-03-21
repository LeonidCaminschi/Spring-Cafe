package cafe.springcafe.repository;

import cafe.springcafe.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(
            value = """
                    SELECT cook_id
                    FROM orders
                    GROUP BY cook_id
                    HAVING COUNT(*) <= 5
                    ORDER BY COUNT(*) ASC
                    LIMIT 1;
                    """,
            nativeQuery = true
    )
    public Long getIdOfCookWithLeastOrders();
}
