package com.pokemon.api.repository;

import com.pokemon.api.model.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRepository extends JpaRepository<Attack, Long> {
}
