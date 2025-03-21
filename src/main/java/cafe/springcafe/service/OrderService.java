package cafe.springcafe.service;

import cafe.springcafe.domain.Order;
import cafe.springcafe.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Long getIdOfCookWithLeastOrders() {
        return repository.getIdOfCookWithLeastOrders();
    }

    public void save(Order order) {
        repository.save(order);
    }

}
