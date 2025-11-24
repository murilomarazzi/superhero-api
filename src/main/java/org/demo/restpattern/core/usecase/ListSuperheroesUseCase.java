package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListSuperheroesUseCase {
    
    private final SuperheroRepository repository;
    
    public List<Superhero> execute() {
        return repository.findAll();
    }
    
    public List<Superhero> executeByName(String name) {
        return repository.findByName(name);
    }
    
    public List<Superhero> executeByPublisher(String publisher) {
        return repository.findByPublisher(publisher);
    }
    
    public List<Superhero> executeByAlignment(String alignment) {
        return repository.findByAlignment(alignment);
    }
}
