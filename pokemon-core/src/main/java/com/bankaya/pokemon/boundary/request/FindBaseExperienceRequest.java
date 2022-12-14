package com.bankaya.pokemon.boundary.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindBaseExperienceRequest extends Request {
    public String name;
}
