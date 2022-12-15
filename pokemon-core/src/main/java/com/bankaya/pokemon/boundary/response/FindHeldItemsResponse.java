package com.bankaya.pokemon.boundary.response;

import com.bankaya.pokemon.boundary.ds.HeldItemDS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindHeldItemsResponse extends Response {
    public List<HeldItemDS> heldItems;
}
