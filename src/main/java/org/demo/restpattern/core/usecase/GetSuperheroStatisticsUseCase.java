package org.demo.restpattern.core.usecase;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.SuperheroStatistics;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSuperheroStatisticsUseCase {
    
    private final SuperheroRepository repository;
    
    public SuperheroStatistics execute() {
        return SuperheroStatistics.builder()
                .total(repository.count())
                .byGoodAlignment(repository.countByAlignment("good"))
                .byBadAlignment(repository.countByAlignment("bad"))
                .byNeutralAlignment(repository.countByAlignment("neutral"))
                .mostCommonPublisher(repository.findMostCommonPublisher().orElse("N/A"))
                .build();
    }
}
