package cafe.springcafe.service;

import cafe.springcafe.domain.Dish;
import cafe.springcafe.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final Logger log = LoggerFactory.getLogger(DishService.class);

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Optional<Dish> getDishbyName(String dishName) {
        return dishRepository.getDishByName(dishName);
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

}
