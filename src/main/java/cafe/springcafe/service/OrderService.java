package cafe.springcafe.service;

import cafe.springcafe.domain.Order;
import cafe.springcafe.projection.ICookOrderCount;
import cafe.springcafe.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<ICookOrderCount> getIdOfCooksWithOrderCount() {
        return repository.getCookOrderCounts();
    }

    public void save(Order order) {
        repository.save(order);
    }

}
