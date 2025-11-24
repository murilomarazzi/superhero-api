package org.demo.restpattern.core.usecase;

import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.port.SuperheroEventPublisher;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSuperheroUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @Mock
    private SuperheroEventPublisher eventPublisher;

    @InjectMocks
    private DeleteSuperheroUseCase useCase;

    @Test
    void shouldDeleteSuperheroSuccessfullyWhenExists() {
        when(repository.existsById(123L)).thenReturn(true);

        useCase.execute(123L);

        verify(repository).deleteById(123L);
        verify(eventPublisher).publishSuperheroDeleted(123L);
    }

    @Test
    void shouldThrowExceptionWhenSuperheroNotFound() {
        when(repository.existsById(999L)).thenReturn(false);

        assertThrows(SuperheroNotFoundException.class, () -> useCase.execute(999L));
        verify(repository, never()).deleteById(anyLong());
        verify(eventPublisher, never()).publishSuperheroDeleted(anyLong());
    }
}
