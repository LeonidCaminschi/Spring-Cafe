package cafe.springcafe.service;

import cafe.springcafe.domain.Cook;
import cafe.springcafe.repository.CookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CookService {

    private final Logger LOG = LoggerFactory.getLogger(CookService.class);

    private final CookRepository cookRepository;

    public CookService(CookRepository repository) {
        this.cookRepository = repository;
    }

    public Optional<Cook> getCookById(Long id){
        return cookRepository.getCookById(id);
    }

    public void save(Cook cook){
        cookRepository.save(cook);
    }
}
