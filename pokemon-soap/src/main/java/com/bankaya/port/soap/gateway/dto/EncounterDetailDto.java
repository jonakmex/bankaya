package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class EncounterDetailDto {
    public Integer chance;
    @JsonProperty("condition_values")
    public Map<String,String> conditionValues;
    @JsonProperty("min_level")
    public Integer minLevel;
    @JsonProperty("max_level")
    public Integer maxLevel;
    public Map<String,String> method;
}
