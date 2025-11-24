package org.demo.restpattern.core.port;

import org.demo.restpattern.core.domain.Superhero;

public interface SuperheroEventPublisher {
    void publishSuperheroCreated(Superhero superhero);
    void publishSuperheroUpdated(Superhero superhero);
    void publishSuperheroDeleted(long superheroId);
}
