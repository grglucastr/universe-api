package com.grglucastr.universeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlanetRequestDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mass")
    private Double mass;
}
