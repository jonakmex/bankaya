package com.bankaya.test.bdd;

import com.bankaya.pokemon.entity.Ability;
import com.bankaya.pokemon.entity.HeldDetail;
import com.bankaya.pokemon.entity.HeldItem;
import com.bankaya.pokemon.entity.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    public static Pokemon newPokemonWithAbilities(String pokemonName){
        return  new Pokemon((new Random().nextLong(100)),pokemonName,makeRandomAbilities(),makeRandomHeldItems(),(new Random().nextInt(10)));
    }

    private static List<Ability> makeRandomAbilities() {
        return Arrays.asList(new Ability("ability a",Boolean.FALSE,10)
                            ,new Ability("ability b",Boolean.TRUE,1));
    }

    private static List<HeldItem> makeRandomHeldItems() {
        return Arrays.asList(
                new HeldItem("held-item-1",makeRandomHeldDetails())
                ,new HeldItem("held-item-2",makeRandomHeldDetails())
        );
    }

    private static List<HeldDetail> makeRandomHeldDetails() {
        List<HeldDetail> heldDetails = Arrays.asList(
                new HeldDetail("detail-1",1)
                ,new HeldDetail("detail-2",2)
        );
        return heldDetails;
    }
}
