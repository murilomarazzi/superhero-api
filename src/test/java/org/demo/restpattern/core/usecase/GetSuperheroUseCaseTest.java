package org.demo.restpattern.core.usecase;

import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSuperheroUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @InjectMocks
    private GetSuperheroUseCase useCase;

    @Test
    void shouldReturnSuperheroWhenExists() {
        var superhero = Superhero.builder()
                .id(123L)
                .name("Batman")
                .fullName("Bruce Wayne")
                .build();

        when(repository.findById(123L)).thenReturn(Optional.of(superhero));

        var result = useCase.execute(123L);

        assertNotNull(result);
        assertEquals(123L, result.getId());
        assertEquals("Batman", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenSuperheroNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(SuperheroNotFoundException.class, () -> useCase.execute(999L));
    }
}
