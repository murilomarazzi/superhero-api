package org.demo.restpattern.core.port;

import org.demo.restpattern.core.domain.Superhero;

import java.util.List;
import java.util.Optional;

public interface SuperheroRepository {
    Superhero save(Superhero superhero);
    Optional<Superhero> findById(long id);
    List<Superhero> findAll();
    List<Superhero> findByName(String name);
    List<Superhero> findByPublisher(String publisher);
    List<Superhero> findByAlignment(String alignment);
    void deleteById(long id);
    boolean existsById(long id);
    long count();
    long countByAlignment(String alignment);
    Optional<String> findMostCommonPublisher();
}
