package com.bankaya.pokemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    private Long id;
    private String name;
    private List<Ability> abilities;
    private List<HeldItem> heldItems;
    private Integer baseExperience;
    private List<Encounter> encounters;
}
