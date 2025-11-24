package org.demo.restpattern.api.mapper;

import org.demo.restpattern.api.dto.SuperheroRequest;
import org.demo.restpattern.api.dto.SuperheroResponse;
import org.demo.restpattern.api.dto.SuperheroStatisticsResponse;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.domain.SuperheroStatistics;
import org.springframework.stereotype.Component;

@Component
public class SuperheroMapper {
    
    public Superhero toDomain(SuperheroRequest request) {
        if (request == null) {
            return null;
        }
        
        return Superhero.builder()
                .name(request.name())
                .fullName(request.fullName())
                .intelligence(request.intelligence())
                .strength(request.strength())
                .speed(request.speed())
                .durability(request.durability())
                .power(request.power())
                .combat(request.combat())
                .gender(request.gender())
                .race(request.race())
                .height(request.height())
                .weight(request.weight())
                .eyeColor(request.eyeColor())
                .hairColor(request.hairColor())
                .alignment(request.alignment())
                .publisher(request.publisher())
                .build();
    }
    
    public SuperheroResponse toResponse(Superhero domain) {
        if (domain == null) {
            return null;
        }
        
        return new SuperheroResponse(
                domain.getId(),
                domain.getName(),
                domain.getFullName(),
                domain.getIntelligence(),
                domain.getStrength(),
                domain.getSpeed(),
                domain.getDurability(),
                domain.getPower(),
                domain.getCombat(),
                domain.getGender(),
                domain.getRace(),
                domain.getHeight(),
                domain.getWeight(),
                domain.getEyeColor(),
                domain.getHairColor(),
                domain.getAlignment(),
                domain.getPublisher(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
    
    public SuperheroStatisticsResponse toStatisticsResponse(SuperheroStatistics statistics) {
        if (statistics == null) {
            return null;
        }
        
        return new SuperheroStatisticsResponse(
                statistics.getTotal(),
                statistics.getByGoodAlignment(),
                statistics.getByBadAlignment(),
                statistics.getByNeutralAlignment(),
                statistics.getMostCommonPublisher()
        );
    }
}
