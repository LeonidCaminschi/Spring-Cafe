package cafe.springcafe.service;

import cafe.springcafe.domain.Cook;
import cafe.springcafe.repository.CookRepository;
import org.springframework.stereotype.Service;

@Service
public class CookService {

    private CookRepository cookRepository;

    public CookService(CookRepository repository) {
        this.cookRepository = repository;
    }

    public Cook getCookById(Long id){
        return cookRepository.getCookById(id);
    }

    public void save(Cook cook){
        cookRepository.save(cook);
    }
}
