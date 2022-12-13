package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonDto {
    public List<AbilityDto> abilities;
    @JsonProperty("base_experience")
    public Integer baseExperience;
    @JsonProperty("held_items")
    public List<HeldItemDto> heldItems;
    public Long id;
    public String name;
    @JsonProperty("location_area_encounters")
    public String locationAreaEncounters;
}
