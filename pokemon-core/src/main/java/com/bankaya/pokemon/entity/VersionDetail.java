package com.bankaya.pokemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetail {
    private List<EncounterDetail> encounterDetails;
    private Integer maxChance;
    private String name;
}
