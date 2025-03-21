package cafe.springcafe.web;

import cafe.springcafe.domain.Dish;
import cafe.springcafe.service.DishService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class DishResource {

    private final DishService dishService;

    public DishResource(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getDishes() {
        List<Dish> dishes = dishService.getDishes();
        return ResponseEntity
                .status(OK)
                .body(dishes);
    }

}
