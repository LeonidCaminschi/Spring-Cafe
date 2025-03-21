package cafe.springcafe.web;

import cafe.springcafe.domain.Order;
import cafe.springcafe.service.CookService;
import cafe.springcafe.service.DishService;
import cafe.springcafe.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController("/order")
public class OrderResource {

    private final OrderService orderService;
    private final DishService dishService;
    private final CookService cookService;

    public OrderResource(OrderService orderRepository, DishService dishService, CookService cookService) {
        this.orderService = orderRepository;
        this.dishService = dishService;
        this.cookService = cookService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> countByLeastOrders(
            @RequestBody String dishName
    ) {
        var cookId = orderService.getIdOfCookWithLeastOrders();
        if (cookId != null) {
            return ResponseEntity
                    .status(OK)
                    .body("All Cooks are busy at the current time");
        }

        var cook = cookService.getCookById(cookId);

        var dish = dishService.getDishbyName(dishName);
        if (dish == null) {
            return ResponseEntity
                    .status(OK)
                    .body("Dish not found in the menu");
        }

        orderService.save(new Order(cook, dish));

        return ResponseEntity
                .status(OK)
                .body("Succesfuly added order");
    }

}
