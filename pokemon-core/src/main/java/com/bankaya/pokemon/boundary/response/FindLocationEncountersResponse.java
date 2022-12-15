package com.bankaya.pokemon.boundary.response;

import com.bankaya.pokemon.boundary.ds.EncounterDS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindLocationEncountersResponse extends Response {
    public List<EncounterDS> encounters;
}
