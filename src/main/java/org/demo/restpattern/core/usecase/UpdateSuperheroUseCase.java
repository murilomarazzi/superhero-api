package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateSuperheroUseCase {
    
    private final SuperheroRepository repository;
    private final SuperheroEventPublisher eventPublisher;
    
    public Superhero execute(long id, Superhero superhero) {
        if (!repository.existsById(id)) {
            throw new SuperheroNotFoundException("Superhero not found with id: " + id);
        }
        
        validateSuperhero(superhero);
        
        superhero.setId(id);
        superhero.setUpdatedAt(LocalDateTime.now());
        
        Superhero updatedSuperhero = repository.save(superhero);
        eventPublisher.publishSuperheroUpdated(updatedSuperhero);
        
        return updatedSuperhero;
    }
    
    private void validateSuperhero(Superhero superhero) {
        if (superhero.getName() == null || superhero.getName().isBlank()) {
            throw new InvalidSuperheroDataException("Superhero name is required");
        }
    }
}
