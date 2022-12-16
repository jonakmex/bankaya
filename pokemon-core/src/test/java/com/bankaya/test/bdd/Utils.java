package com.bankaya.test.bdd;

import com.bankaya.pokemon.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    public static Pokemon newPokemonWithAbilities(String pokemonName){
        return  new Pokemon(1L,pokemonName,makeRandomAbilities(),makeRandomHeldItems(),(new Random().nextInt(10)),makeRandomEncounters());
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

    private static List<Encounter> makeRandomEncounters(){
        return Arrays.asList(
                new Encounter("encounter-1",makeVersionDetails())
        );
    }

    private static List<VersionDetail> makeVersionDetails() {
        return Arrays.asList(
                new VersionDetail(makeEncounterDetails(),10,"XYZ")
        );
    }

    private static List<EncounterDetail> makeEncounterDetails() {
        return Arrays.asList(
                new EncounterDetail(   10
                                    ,"conditional"
                                    ,0
                                    ,100
                                    ,"method")
        );
    }
}
