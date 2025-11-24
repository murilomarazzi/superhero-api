package org.demo.restpattern.api.dto;

public record SuperheroStatisticsResponse(
        Long total,
        Long byGoodAlignment,
        Long byBadAlignment,
        Long byNeutralAlignment,
        String mostCommonPublisher
) {}
