package com.grglucastr.universeapi.controllers.v1;

import com.grglucastr.universeapi.models.Planet;
import com.grglucastr.universeapi.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Planet> postPlanet(@RequestBody Planet planet){
        final Planet newPlanet = planetRepository.save(planet);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPlanet.getId()).toUri();
        return ResponseEntity.created(uri).body(newPlanet);
    }

    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets(){
        final List<Planet> planets = planetRepository.findAll();
        return ResponseEntity.ok(planets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getSinglePlanet(@PathVariable("id") Integer id){
        final Optional<Planet> planetFound = planetRepository.findById(id);
        return planetFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
