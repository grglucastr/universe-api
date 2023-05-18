package com.grglucastr.universeapi.v1.controllers;

import com.grglucastr.universeapi.v1.dto.PlanetRequestDTO;
import com.grglucastr.universeapi.v1.models.Planet;
import com.grglucastr.universeapi.v1.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/planets")
@AllArgsConstructor
public class PlanetController {

    private final PlanetRepository planetRepository;

    @PostMapping
    public ResponseEntity<Planet> postPlanet(@RequestBody Planet planet) {
        final Planet newPlanet = planetRepository.save(planet);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPlanet.getId()).toUri();
        return ResponseEntity.created(uri).body(newPlanet);
    }

    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets() {
        final List<Planet> planets = planetRepository.findAll();
        return ResponseEntity.ok(planets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getSinglePlanet(@PathVariable("id") Integer id) {
        final Optional<Planet> planetFound = planetRepository.findById(id);
        return planetFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planet> updateSinglePlanet(@PathVariable("id") Integer id, @RequestBody Planet reqPlanet) {
        final Optional<Planet> planetFound = planetRepository.findById(id);
        if (planetFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        final Planet planet = planetFound.get();
        planet.setMass(reqPlanet.getMass());
        planet.setName(reqPlanet.getName());
        final Planet planetUpdate = planetRepository.save(planet);
        return ResponseEntity.ok(planetUpdate);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Planet> updatePlanetPatch(@PathVariable("id") Integer id, @RequestBody PlanetRequestDTO planetRequestDTO) {
        final Optional<Planet> planetFound = planetRepository.findById(id);
        if (planetFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final Planet planet = planetFound.get();
        if (planetRequestDTO.getMass() != null) {
            planet.setMass(planetRequestDTO.getMass());
        }

        if (planetRequestDTO.getName() != null) {
            planet.setName(planetRequestDTO.getName());
        }

        final Planet planetUpdated = planetRepository.save(planet);
        return ResponseEntity.ok(planetUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Planet> deletePlanet(@PathVariable("id") Integer id) {
        final Optional<Planet> planetFound = planetRepository.findById(id);
        if (planetFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        planetFound.ifPresent(planetRepository::delete);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
