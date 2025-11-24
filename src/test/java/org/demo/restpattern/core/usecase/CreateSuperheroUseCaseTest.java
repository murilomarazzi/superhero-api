package org.demo.restpattern.core.usecase;

import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSuperheroUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @Mock
    private SuperheroEventPublisher eventPublisher;

    @InjectMocks
    private CreateSuperheroUseCase useCase;

    @Test
    void shouldCreateSuperheroSuccessfullyWhenValidDataProvided() {
        var superhero = Superhero.builder()
                .name("Superman")
                .fullName("Clark Kent")
                .alignment("good")
                .publisher("DC Comics")
                .build();

        var savedSuperhero = Superhero.builder()
                .id(123L)
                .name("Superman")
                .fullName("Clark Kent")
                .alignment("good")
                .publisher("DC Comics")
                .build();

        when(repository.save(any(Superhero.class))).thenReturn(savedSuperhero);

        var result = useCase.execute(superhero);

        assertNotNull(result);
        assertEquals(123L, result.getId());
        assertEquals("Superman", result.getName());
        verify(repository).save(any(Superhero.class));
        verify(eventPublisher).publishSuperheroCreated(savedSuperhero);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        var superhero = Superhero.builder()
                .fullName("Clark Kent")
                .build();

        assertThrows(InvalidSuperheroDataException.class, () -> useCase.execute(superhero));
        verify(repository, never()).save(any());
        verify(eventPublisher, never()).publishSuperheroCreated(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        var superhero = Superhero.builder()
                .name("")
                .fullName("Clark Kent")
                .build();

        assertThrows(InvalidSuperheroDataException.class, () -> useCase.execute(superhero));
        verify(repository, never()).save(any());
        verify(eventPublisher, never()).publishSuperheroCreated(any());
    }
}



