package cafe.springcafe.web;

import cafe.springcafe.config.Config;
import cafe.springcafe.domain.Order;
import cafe.springcafe.dto.CreateOrderRequest;
import cafe.springcafe.projection.ICookOrderCount;
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

import java.util.List;

import static cafe.springcafe.config.Config.*;
import static org.springframework.http.HttpStatus.OK;

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

        String dishName = request.dish();

        List<ICookOrderCount> cooksOrderCounts = orderService.getIdOfCooksWithOrderCount();
        if (cooksOrderCounts.isEmpty()) {
            return ResponseEntity
                    .status(OK)
                    .body(Config.NO_CURRENT_COOKS_EMPLOYED_AT_THE_FACILITY);
        }

        var dish = dishService.getDishbyName(dishName);

        if (dish.isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(DISH_NOT_FOUND_IN_THE_MENU);
        }

        ICookOrderCount minCookOrderCountResponse = cooksOrderCounts.get(1);
        for (ICookOrderCount cookOrderCountResponse : cooksOrderCounts) {
            if (cookOrderCountResponse.getACount() >= 5) {
                continue;
            }
            if (minCookOrderCountResponse.getACount() > cookOrderCountResponse.getACount()) {
                minCookOrderCountResponse = cookOrderCountResponse;
            }
        }

        if (minCookOrderCountResponse.getACount() >= 5) {
            return ResponseEntity
                    .status(OK)
                    .body(ALL_COOKS_ARE_BUSY_AT_THE_MOMENT);
        }

        var cookTakingOrder = cookService.getCookById(minCookOrderCountResponse.getCookId());

        if (cookTakingOrder.isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body(COOK_NOT_FOUND_FOR_THE_GIVEN_ID);
        }
        
        orderService.save(new Order(cookTakingOrder.get(), dish.get()));

        return ResponseEntity
                .status(OK)
                .body(SUCCESFULY_ADDED_ORDER);
    }

}
