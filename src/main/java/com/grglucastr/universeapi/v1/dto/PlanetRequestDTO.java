package com.grglucastr.universeapi.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlanetRequestDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mass")
    private Double mass;
}
