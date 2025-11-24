package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateSuperheroUseCase {
    
    private final SuperheroRepository repository;
    private final SuperheroEventPublisher eventPublisher;
    
    public Superhero execute(Superhero superhero) {
        validateSuperhero(superhero);
        
        superhero.setCreatedAt(LocalDateTime.now());
        superhero.setUpdatedAt(LocalDateTime.now());
        
        Superhero savedSuperhero = repository.save(superhero);
        eventPublisher.publishSuperheroCreated(savedSuperhero);
        
        return savedSuperhero;
    }
    
    private void validateSuperhero(Superhero superhero) {
        if (superhero.getName() == null || superhero.getName().isBlank()) {
            throw new InvalidSuperheroDataException("Superhero name is required");
        }
    }
}
