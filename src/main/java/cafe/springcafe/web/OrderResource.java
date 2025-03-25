package cafe.springcafe.web;

import cafe.springcafe.dto.CookOrderCount;
import cafe.springcafe.domain.Order;
import cafe.springcafe.dto.CreateOrderRequest;
import cafe.springcafe.service.CookService;
import cafe.springcafe.service.DishService;
import cafe.springcafe.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

import java.util.Map;

@RestController
public class OrderResource {

    private final OrderService orderService;
    private final DishService dishService;
    private final CookService cookService;

    public OrderResource(OrderService orderRepository, DishService dishService, CookService cookService) {
        this.orderService = orderRepository;
        this.dishService = dishService;
        this.cookService = cookService;
        }

        @Operation(summary = "Create an order", description = "Assign a dish to the cook with the least number of orders.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input or no available cooks")
        })
        @PostMapping("/order")
        public ResponseEntity<String> countByLeastOrders(
            @RequestBody CreateOrderRequest request) {

        String dishName = request.getDish();

        var cooksOrderCounts = orderService.getIdOfCookWithLeastOrders();
        if (cooksOrderCounts == null) {
            return ResponseEntity
                    .status(OK)
                    .body("No current cooks employed at the facility");
        }

        var dish = dishService.getDishbyName(dishName);
        if (dish == null) {
            return ResponseEntity
                    .status(OK)
                    .body("Dish not found in the menu");
        }

        CookOrderCount minCookOrderCount = cooksOrderCounts.get(1);
        for (CookOrderCount cookOrderCount : cooksOrderCounts) {
            if (cookOrderCount.getCount() >= 5) {
                continue;
            }
            if (minCookOrderCount.getCount() > cookOrderCount.getCount()) {
                minCookOrderCount = cookOrderCount;
            }
        }

        if (minCookOrderCount.getCount() >= 5) {
            return ResponseEntity
                    .status(OK)
                    .body("All cooks are busy at the moment");
        }

        var cookTakingOrder = cookService.getCookById(minCookOrderCount.getCook_id());

        orderService.save(new Order(cookTakingOrder, dish));

        return ResponseEntity
                .status(OK)
                .body("Succesfuly added order");
    }

}
