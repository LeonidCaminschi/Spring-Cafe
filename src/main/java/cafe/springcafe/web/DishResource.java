package cafe.springcafe.web;

import cafe.springcafe.domain.Dish;
import cafe.springcafe.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class DishResource {

    private final DishService dishService;

    public DishResource(DishService dishService) {
        this.dishService = dishService;
    }

    @Operation(summary = "Get all dishes", description = "Retrieve a list of all dishes available in the menu.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of dishes")
    })
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getDishes() {
        List<Dish> dishes = dishService.getDishes();
        return ResponseEntity
                .status(OK)
                .body(dishes);
    }

}
