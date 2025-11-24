package org.demo.restpattern.core.usecase;

import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListSuperheroesUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @InjectMocks
    private ListSuperheroesUseCase useCase;

    @Test
    void shouldReturnAllSuperheroesWhenNoFilterApplied() {
        var superheroes = Arrays.asList(
                Superhero.builder().id(1L).name("Superman").build(),
                Superhero.builder().id(2L).name("Batman").build()
        );

        when(repository.findAll()).thenReturn(superheroes);

        var result = useCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnFilteredSuperheroesWhenSearchingByName() {
        var superheroes = List.of(
                Superhero.builder().id(1L).name("Superman").build()
        );

        when(repository.findByName("Super")).thenReturn(superheroes);

        var result = useCase.executeByName("Super");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Superman", result.get(0).getName());
    }

    @Test
    void shouldReturnFilteredSuperheroesWhenSearchingByPublisher() {
        var superheroes = Arrays.asList(
                Superhero.builder().id(1L).name("Superman").publisher("DC Comics").build(),
                Superhero.builder().id(2L).name("Batman").publisher("DC Comics").build()
        );

        when(repository.findByPublisher("DC Comics")).thenReturn(superheroes);

        var result = useCase.executeByPublisher("DC Comics");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnFilteredSuperheroesWhenSearchingByAlignment() {
        var superheroes = List.of(
                Superhero.builder().id(1L).name("Superman").alignment("good").build()
        );

        when(repository.findByAlignment("good")).thenReturn(superheroes);

        var result = useCase.executeByAlignment("good");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("good", result.get(0).getAlignment());
    }
}
