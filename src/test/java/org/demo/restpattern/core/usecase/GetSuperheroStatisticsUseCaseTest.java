package org.demo.restpattern.core.usecase;

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
class GetSuperheroStatisticsUseCaseTest {

    @Mock
    private SuperheroRepository repository;

    @InjectMocks
    private GetSuperheroStatisticsUseCase useCase;

    @Test
    void shouldReturnStatisticsSuccessfully() {
        when(repository.count()).thenReturn(100L);
        when(repository.countByAlignment("good")).thenReturn(60L);
        when(repository.countByAlignment("bad")).thenReturn(30L);
        when(repository.countByAlignment("neutral")).thenReturn(10L);
        when(repository.findMostCommonPublisher()).thenReturn(Optional.of("Marvel Comics"));

        var result = useCase.execute();

        assertNotNull(result);
        assertEquals(100L, result.getTotal());
        assertEquals(60L, result.getByGoodAlignment());
        assertEquals(30L, result.getByBadAlignment());
        assertEquals(10L, result.getByNeutralAlignment());
        assertEquals("Marvel Comics", result.getMostCommonPublisher());
    }

    @Test
    void shouldReturnNAWhenNoPublisherFound() {
        when(repository.count()).thenReturn(0L);
        when(repository.countByAlignment("good")).thenReturn(0L);
        when(repository.countByAlignment("bad")).thenReturn(0L);
        when(repository.countByAlignment("neutral")).thenReturn(0L);
        when(repository.findMostCommonPublisher()).thenReturn(Optional.empty());

        var result = useCase.execute();

        assertNotNull(result);
        assertEquals("N/A", result.getMostCommonPublisher());
    }
}

