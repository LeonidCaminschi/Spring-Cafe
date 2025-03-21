package cafe.springcafe.service;

import cafe.springcafe.domain.Dish;
import cafe.springcafe.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish getDishbyName(String dishName) {
        return dishRepository.getDishByName(dishName);
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

}
