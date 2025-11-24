package org.demo.restpattern.dataprovider.repository;

import lombok.RequiredArgsConstructor;
import org.demo.restpattern.core.domain.Superhero;
import org.demo.restpattern.core.port.SuperheroRepository;
import org.demo.restpattern.dataprovider.repository.mapper.SuperheroEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SuperheroRepositoryImpl implements SuperheroRepository {
    
    private final SuperheroJpaRepository jpaRepository;
    private final SuperheroEntityMapper mapper;
    
    @Override
    public Superhero save(Superhero superhero) {
        var entity = mapper.toEntity(superhero);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Superhero> findById(long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
    
    @Override
    public List<Superhero> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
    
    @Override
    public List<Superhero> findByName(String name) {
        return jpaRepository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toDomain)
                .toList();
    }
    
    @Override
    public List<Superhero> findByPublisher(String publisher) {
        return jpaRepository.findByPublisherIgnoreCase(publisher).stream()
                .map(mapper::toDomain)
                .toList();
    }
    
    @Override
    public List<Superhero> findByAlignment(String alignment) {
        return jpaRepository.findByAlignment(alignment).stream()
                .map(mapper::toDomain)
                .toList();
    }
    
    @Override
    public void deleteById(long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(long id) {
        return jpaRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaRepository.count();
    }
    
    @Override
    public long countByAlignment(String alignment) {
        return jpaRepository.countByAlignment(alignment);
    }
    
    @Override
    public Optional<String> findMostCommonPublisher() {
        var publishers = jpaRepository.findMostCommonPublisher(org.springframework.data.domain.PageRequest.of(0, 1));
        return publishers.isEmpty() ? Optional.empty() : Optional.of(publishers.get(0));
    }
}
