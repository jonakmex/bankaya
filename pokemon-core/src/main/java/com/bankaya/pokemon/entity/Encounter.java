package com.bankaya.pokemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encounter {
    private String locationArea;
    private List<EncounterDetail> encounterDetails;
}
