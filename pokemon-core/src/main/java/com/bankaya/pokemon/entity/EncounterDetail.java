package com.bankaya.pokemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncounterDetail {
    private Integer chance;
    private String conditionValues;
    private Integer minLevel;
    private Integer maxLevel;
    private String method;
}
