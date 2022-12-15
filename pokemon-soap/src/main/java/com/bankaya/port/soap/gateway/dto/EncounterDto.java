package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EncounterDto {
    @JsonProperty("location_area")
    public Map<String,String> locationArea;
    @JsonProperty("version_details")
    public List<EncounterDetailDto> encounterDetails;
}
