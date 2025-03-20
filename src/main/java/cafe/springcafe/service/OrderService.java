package cafe.springcafe.service;

import cafe.springcafe.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Long countByLeastOrders() {
        return repository.getIdOfCookWithLeastOrders();
    }

}
