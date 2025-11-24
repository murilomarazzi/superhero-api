package org.demo.restpattern.dataprovider.repository.mapper;

import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.dataprovider.repository.entity.SuperheroEntity;
import org.springframework.stereotype.Component;

@Component
public class SuperheroEntityMapper {
    
    public SuperheroEntity toEntity(Superhero domain) {
        if (domain == null) {
            return null;
        }
        
        var builder = SuperheroEntity.builder();

        // Only set ID if it's greater than 0 (i.e., existing entity)
        if (domain.getId() > 0) {
            builder.id(domain.getId());
        }

        return builder
                .name(domain.getName())
                .fullName(domain.getFullName())
                .intelligence(domain.getIntelligence())
                .strength(domain.getStrength())
                .speed(domain.getSpeed())
                .durability(domain.getDurability())
                .power(domain.getPower())
                .combat(domain.getCombat())
                .gender(domain.getGender())
                .race(domain.getRace())
                .height(domain.getHeight())
                .weight(domain.getWeight())
                .eyeColor(domain.getEyeColor())
                .hairColor(domain.getHairColor())
                .alignment(domain.getAlignment())
                .publisher(domain.getPublisher())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
    
    public Superhero toDomain(SuperheroEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Superhero.builder()
                .id(entity.getId() != null ? entity.getId() : 0L)
                .name(entity.getName())
                .fullName(entity.getFullName())
                .intelligence(entity.getIntelligence())
                .strength(entity.getStrength())
                .speed(entity.getSpeed())
                .durability(entity.getDurability())
                .power(entity.getPower())
                .combat(entity.getCombat())
                .gender(entity.getGender())
                .race(entity.getRace())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .eyeColor(entity.getEyeColor())
                .hairColor(entity.getHairColor())
                .alignment(entity.getAlignment())
                .publisher(entity.getPublisher())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
