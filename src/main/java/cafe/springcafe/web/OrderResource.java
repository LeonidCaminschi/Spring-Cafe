package cafe.springcafe.web;

import cafe.springcafe.repository.OrderRepository;
import cafe.springcafe.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController("/order")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderRepository) {
        this.orderService = orderRepository;
    }

    @GetMapping("/count-least")
    public ResponseEntity<Long> countByLeastOrders() {
        var count = orderService.countByLeastOrders();
        return ResponseEntity
                .status(OK)
                .body(count);
    }

}
