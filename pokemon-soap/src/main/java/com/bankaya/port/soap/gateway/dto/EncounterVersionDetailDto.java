package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EncounterVersionDetailDto {
    @JsonProperty("encounter_details")
    private Map encounterDetailDtos;
}
