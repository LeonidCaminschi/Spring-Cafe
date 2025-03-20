package cafe.springcafe.service;

import cafe.springcafe.domain.Cook;
import cafe.springcafe.repository.CookRepository;
import org.springframework.stereotype.Service;

@Service
public class CookService {

    private CookRepository repository;

    public CookService(CookRepository repository) {
        this.repository = repository;
    }

    void save(Cook cook) {
        repository.save(cook);
    }
}
