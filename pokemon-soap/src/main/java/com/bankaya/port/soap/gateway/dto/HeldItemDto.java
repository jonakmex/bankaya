package com.bankaya.port.soap.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HeldItemDto {
    public Map<String,String> item;
    @JsonProperty("version_details")
    public List<VersionDetailDto> versionDetails;
}
