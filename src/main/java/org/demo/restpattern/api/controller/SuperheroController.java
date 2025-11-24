package org.demo.restpattern.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.demo.restpattern.api.dto.SuperheroRequest;
import org.demo.restpattern.api.dto.SuperheroResponse;
import org.demo.restpattern.api.dto.SuperheroStatisticsResponse;
import org.demo.restpattern.api.mapper.SuperheroMapper;
import org.demo.restpattern.core.usecase.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/superheroes")
@RequiredArgsConstructor
public class SuperheroController {
    
    private final CreateSuperheroUseCase createSuperheroUseCase;
    private final GetSuperheroUseCase getSuperheroUseCase;
    private final ListSuperheroesUseCase listSuperheroesUseCase;
    private final UpdateSuperheroUseCase updateSuperheroUseCase;
    private final DeleteSuperheroUseCase deleteSuperheroUseCase;
    private final GetSuperheroStatisticsUseCase getSuperheroStatisticsUseCase;
    private final SuperheroMapper mapper;
    
    @PostMapping
    @CacheEvict(value = "superheroes", allEntries = true)
    public ResponseEntity<SuperheroResponse> createSuperhero(@Valid @RequestBody SuperheroRequest request) {
        var superhero = mapper.toDomain(request);
        var created = createSuperheroUseCase.execute(superhero);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }
    
    @GetMapping("/{id}")
    @Cacheable(value = "superheroes", key = "#id")
    public ResponseEntity<SuperheroResponse> getSuperhero(@PathVariable Long id) {
        var superhero = getSuperheroUseCase.execute(id);
        return ResponseEntity.ok(mapper.toResponse(superhero));
    }
    
    @GetMapping
    @Cacheable(value = "superheroes")
    public ResponseEntity<List<SuperheroResponse>> listSuperheroes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String alignment) {
        
        List<SuperheroResponse> superheroes;
        
        if (name != null) {
            superheroes = listSuperheroesUseCase.executeByName(name).stream()
                    .map(mapper::toResponse)
                    .toList();
        } else if (publisher != null) {
            superheroes = listSuperheroesUseCase.executeByPublisher(publisher).stream()
                    .map(mapper::toResponse)
                    .toList();
        } else if (alignment != null) {
            superheroes = listSuperheroesUseCase.executeByAlignment(alignment).stream()
                    .map(mapper::toResponse)
                    .toList();
        } else {
            superheroes = listSuperheroesUseCase.execute().stream()
                    .map(mapper::toResponse)
                    .toList();
        }
        
        return ResponseEntity.ok(superheroes);
    }
    
    @PutMapping("/{id}")
    @CacheEvict(value = "superheroes", allEntries = true)
    public ResponseEntity<SuperheroResponse> updateSuperhero(
            @PathVariable Long id,
            @Valid @RequestBody SuperheroRequest request) {
        var superhero = mapper.toDomain(request);
        var updated = updateSuperheroUseCase.execute(id, superhero);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }
    
    @DeleteMapping("/{id}")
    @CacheEvict(value = "superheroes", allEntries = true)
    public ResponseEntity<Void> deleteSuperhero(@PathVariable Long id) {
        deleteSuperheroUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/statistics")
    public ResponseEntity<SuperheroStatisticsResponse> getStatistics() {
        var statistics = getSuperheroStatisticsUseCase.execute();
        return ResponseEntity.ok(mapper.toStatisticsResponse(statistics));
    }
}
