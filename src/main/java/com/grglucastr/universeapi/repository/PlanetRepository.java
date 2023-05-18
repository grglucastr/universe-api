package com.grglucastr.universeapi.repository;

import com.grglucastr.universeapi.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
