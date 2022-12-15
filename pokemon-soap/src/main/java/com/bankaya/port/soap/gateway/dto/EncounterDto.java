package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EncounterDto {
    @JsonProperty("location_area")
    public String locationArea;
    @JsonProperty("version_details")
    public List<EncounterDetailDto> encounterDetails;
}
