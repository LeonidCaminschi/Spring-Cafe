package cafe.springcafe.repository;

import cafe.springcafe.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(
            value = ("""
                    SELECT * FROM
                    (SELECT cook_id, count(*) FROM order
                    GROUP BY cook_id)
                    """), nativeQuery = true)
    )
    Long getIdOfCookWithLeastOrders();

    Long getCookIdBy();
}
