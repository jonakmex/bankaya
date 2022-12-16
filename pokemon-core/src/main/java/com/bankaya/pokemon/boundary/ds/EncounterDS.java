package com.bankaya.pokemon.boundary.ds;

import lombok.Data;

import java.util.List;

@Data
public class EncounterDS {
    public String locationArea;
    public List<VersionDetailDS> versionDetailDSs;
}
