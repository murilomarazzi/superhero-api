package org.demo.restpattern.dataprovider.repository;

import org.demo.restpattern.dataprovider.repository.entity.SuperheroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroJpaRepository extends JpaRepository<SuperheroEntity, Long> {
    List<SuperheroEntity> findByNameContainingIgnoreCase(String name);
    List<SuperheroEntity> findByPublisherIgnoreCase(String publisher);
    List<SuperheroEntity> findByAlignment(String alignment);
    long countByAlignment(String alignment);

    @Query("SELECT s.publisher FROM SuperheroEntity s GROUP BY s.publisher ORDER BY COUNT(s) DESC")
    List<String> findMostCommonPublisher(Pageable pageable);
}

