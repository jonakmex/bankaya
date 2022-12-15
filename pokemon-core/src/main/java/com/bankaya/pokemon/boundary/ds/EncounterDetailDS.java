package com.bankaya.pokemon.boundary.ds;

import lombok.Data;

@Data
public class EncounterDetailDS {
    public Integer chance;
    public String conditionValues;
    public Integer minLevel;
    public Integer maxLevel;
    public String method;
}
