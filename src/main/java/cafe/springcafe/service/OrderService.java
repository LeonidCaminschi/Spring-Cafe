package cafe.springcafe.service;

import cafe.springcafe.dto.CookOrderCount;
import cafe.springcafe.domain.Order;
import cafe.springcafe.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<CookOrderCount> getIdOfCookWithLeastOrders() {
        return repository.getCookOrderCounts();
    }

    public void save(Order order) {
        repository.save(order);
    }

}
