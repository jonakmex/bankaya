package com.bankaya.pokemon.boundary.ds;

import com.bankaya.pokemon.entity.HeldDetail;
import lombok.Data;

import java.util.List;

@Data
public class HeldItemDS {
    public String name;
    public List<HeldDetailDS> details;
}
