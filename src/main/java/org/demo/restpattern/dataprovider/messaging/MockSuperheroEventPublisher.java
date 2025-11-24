package org.demo.restpattern.dataprovider.messaging;

import lombok.extern.slf4j.Slf4j;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockSuperheroEventPublisher implements SuperheroEventPublisher {

    @Override
    public void publishSuperheroCreated(Superhero superhero) {
        log.info("Mock Event: Superhero created - ID: {}, Name: {}",
                superhero.getId(), superhero.getName());
    }

    @Override
    public void publishSuperheroUpdated(Superhero superhero) {
        log.info("Mock Event: Superhero updated - ID: {}, Name: {}",
                superhero.getId(), superhero.getName());
    }

    @Override
    public void publishSuperheroDeleted(long superheroId) {
        log.info("Mock Event: Superhero deleted - ID: {}", superheroId);
    }
}
