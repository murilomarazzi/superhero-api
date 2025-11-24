package org.demo.restpattern.dataprovider.messaging.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SuperheroEvent(
        long superheroId,
        String name,
        String eventType,
        LocalDateTime timestamp
) {}
