package com.grglucastr.universeapi.controllers.v1;

import com.grglucastr.universeapi.models.Planet;
import com.grglucastr.universeapi.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/planets")
@AllArgsConstructor
public class PlanetController {

    private final PlanetRepository planetRepository;

    @PostMapping
    public ResponseEntity<Planet> postPlanet(){
        final Planet newPlanet = planetRepository.save(new Planet(null, "Earth", new BigDecimal("233.33")));
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPlanet.getId()).toUri();
        return ResponseEntity.created(uri).body(newPlanet);
    }

}
