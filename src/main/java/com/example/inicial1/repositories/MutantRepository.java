package com.example.inicial1.repositories;

import com.example.inicial1.entities.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
    long countByEsMutantTrue();

    long countByEsMutantFalse();
}