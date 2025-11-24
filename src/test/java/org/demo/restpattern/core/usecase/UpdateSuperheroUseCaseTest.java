package org.demo.restpattern.core.usecase;

import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
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
class UpdateSuperheroUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @Mock
    private SuperheroEventPublisher eventPublisher;

    @InjectMocks
    private UpdateSuperheroUseCase useCase;

    @Test
    void shouldUpdateSuperheroSuccessfullyWhenExists() {
        var superhero = Superhero.builder()
                .name("Superman")
                .fullName("Clark Kent")
                .alignment("good")
                .build();

        var updatedSuperhero = Superhero.builder()
                .id(123L)
                .name("Superman")
                .fullName("Clark Kent")
                .alignment("good")
                .build();

        when(repository.existsById(123L)).thenReturn(true);
        when(repository.save(any(Superhero.class))).thenReturn(updatedSuperhero);

        var result = useCase.execute(123L, superhero);

        assertNotNull(result);
        assertEquals(123L, result.getId());
        verify(repository).save(any(Superhero.class));
        verify(eventPublisher).publishSuperheroUpdated(updatedSuperhero);
    }

    @Test
    void shouldThrowExceptionWhenSuperheroNotFound() {
        var superhero = Superhero.builder()
                .name("Superman")
                .build();

        when(repository.existsById(999L)).thenReturn(false);

        assertThrows(SuperheroNotFoundException.class, () -> useCase.execute(999L, superhero));
        verify(repository, never()).save(any());
        verify(eventPublisher, never()).publishSuperheroUpdated(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        var superhero = Superhero.builder()
                .fullName("Clark Kent")
                .build();

        when(repository.existsById(123L)).thenReturn(true);

        assertThrows(InvalidSuperheroDataException.class, () -> useCase.execute(123L, superhero));
        verify(repository, never()).save(any());
    }
}
