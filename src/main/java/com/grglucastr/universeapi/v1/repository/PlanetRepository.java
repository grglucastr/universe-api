package com.grglucastr.universeapi.v1.repository;

import com.grglucastr.universeapi.v1.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
