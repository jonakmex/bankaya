package com.bankaya.pokemon.boundary.ds;

import lombok.Data;

import java.util.List;

@Data
public class VersionDetailDS {
    public List<EncounterDetailDS> encounterDetails;
    public Integer maxChance;
    public String name;
}
