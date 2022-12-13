package com.bankaya.pokemon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {
    private String name;
    private Boolean hidden;
    private Integer slot;
}
