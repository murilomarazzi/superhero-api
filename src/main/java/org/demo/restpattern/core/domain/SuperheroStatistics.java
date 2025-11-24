package org.demo.restpattern.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperheroStatistics {
    private Long total;
    private Long byGoodAlignment;
    private Long byBadAlignment;
    private Long byNeutralAlignment;
    private String mostCommonPublisher;
}
