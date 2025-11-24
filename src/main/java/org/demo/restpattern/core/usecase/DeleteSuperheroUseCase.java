package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSuperheroUseCase {
    
    private final SuperheroRepository repository;
    private final SuperheroEventPublisher eventPublisher;
    
    public void execute(long id) {
        if (!repository.existsById(id)) {
            throw new SuperheroNotFoundException("Superhero not found with id: " + id);
        }
        
        repository.deleteById(id);
        eventPublisher.publishSuperheroDeleted(id);
    }
}
