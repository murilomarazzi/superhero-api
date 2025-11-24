package org.demo.restpattern.api.controller;

import org.demo.restpattern.api.dto.SuperheroRequest;
import org.demo.restpattern.api.dto.SuperheroResponse;
import org.demo.restpattern.api.mapper.SuperheroMapper;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.demo.restpattern.core.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuperheroControllerTest {

    @Mock
    private CreateSuperheroUseCase createSuperheroUseCase;

    @Mock
    private GetSuperheroUseCase getSuperheroUseCase;

    @Mock
    private ListSuperheroesUseCase listSuperheroesUseCase;

    @Mock
    private UpdateSuperheroUseCase updateSuperheroUseCase;

    @Mock
    private DeleteSuperheroUseCase deleteSuperheroUseCase;

    @Mock
    private GetSuperheroStatisticsUseCase getSuperheroStatisticsUseCase;

    @Mock
    private SuperheroMapper mapper;

    @InjectMocks
    private SuperheroController controller;

    @Test
    void shouldCreateSuperheroSuccessfully() {
        var request = new SuperheroRequest("Superman", "Clark Kent", null, null, null, null, null, null, null, null, null, null, null, null, "good", "DC Comics");
        var superhero = Superhero.builder().name("Superman").build();
        var savedSuperhero = Superhero.builder().id(123L).name("Superman").build();
        var response = new SuperheroResponse(123L, "Superman", "Clark Kent", null, null, null, null, null, null, null, null, null, null, null, null, "good", "DC Comics", null, null);

        when(mapper.toDomain(request)).thenReturn(superhero);
        when(createSuperheroUseCase.execute(superhero)).thenReturn(savedSuperhero);
        when(mapper.toResponse(savedSuperhero)).thenReturn(response);

        var result = controller.createSuperhero(request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(123L, result.getBody().id());
        verify(createSuperheroUseCase).execute(superhero);
    }

    @Test
    void shouldGetSuperheroSuccessfully() {
        var superhero = Superhero.builder().id(123L).name("Superman").build();
        var response = new SuperheroResponse(123L, "Superman", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        when(getSuperheroUseCase.execute(123L)).thenReturn(superhero);
        when(mapper.toResponse(superhero)).thenReturn(response);

        var result = controller.getSuperhero(123L);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(123L, result.getBody().id());
        verify(getSuperheroUseCase).execute(123L);
    }

    @Test
    void shouldThrowNotFoundWhenSuperheroDoesNotExist() {
        when(getSuperheroUseCase.execute(999L)).thenThrow(new SuperheroNotFoundException("Not found"));

        assertThrows(SuperheroNotFoundException.class, () -> controller.getSuperhero(999L));
    }

    @Test
    void shouldListAllSuperheroesSuccessfully() {
        var superheroes = Arrays.asList(
                Superhero.builder().id(1L).name("Superman").build(),
                Superhero.builder().id(2L).name("Batman").build()
        );
        var response1 = new SuperheroResponse(1L, "Superman", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        var response2 = new SuperheroResponse(2L, "Batman", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        when(listSuperheroesUseCase.execute()).thenReturn(superheroes);
        when(mapper.toResponse(any())).thenReturn(response1, response2);

        var result = controller.listSuperheroes(null, null, null);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(listSuperheroesUseCase).execute();
    }

    @Test
    void shouldUpdateSuperheroSuccessfully() {
        var request = new SuperheroRequest("Superman", "Clark Kent", null, null, null, null, null, null, null, null, null, null, null, null, "good", null);
        var superhero = Superhero.builder().name("Superman").build();
        var updatedSuperhero = Superhero.builder().id(123L).name("Superman").build();
        var response = new SuperheroResponse(123L, "Superman", "Clark Kent", null, null, null, null, null, null, null, null, null, null, null, null, "good", null, null, null);

        when(mapper.toDomain(request)).thenReturn(superhero);
        when(updateSuperheroUseCase.execute(eq(123L), any())).thenReturn(updatedSuperhero);
        when(mapper.toResponse(updatedSuperhero)).thenReturn(response);

        var result = controller.updateSuperhero(123L, request);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(123L, result.getBody().id());
        verify(updateSuperheroUseCase).execute(eq(123L), any());
    }

    @Test
    void shouldDeleteSuperheroSuccessfully() {
        doNothing().when(deleteSuperheroUseCase).execute(123L);

        var result = controller.deleteSuperhero(123L);

        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(deleteSuperheroUseCase).execute(123L);
    }
}
