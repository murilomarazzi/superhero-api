package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSuperheroUseCase {
    
    private final SuperheroRepository repository;
    
    public Superhero execute(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SuperheroNotFoundException("Superhero not found with id: " + id));
    }
}
