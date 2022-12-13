package com.bankaya.port.soap.gateway.dto;

import lombok.Data;

import java.util.Map;

@Data
public class VersionDetailDto {
    public Integer rarity;
    public Map<String,String> version;
}
