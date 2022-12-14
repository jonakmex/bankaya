package com.bankaya.pokemon.boundary.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindBaseExperienceResponse extends Response {
    public Integer baseExperience;
}
