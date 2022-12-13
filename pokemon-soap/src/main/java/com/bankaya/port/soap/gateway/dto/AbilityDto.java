package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class AbilityDto {
    public Map<String,String> ability;
    @JsonProperty("is_hidden")
    public Boolean hidden;
    public Integer slot;
}
