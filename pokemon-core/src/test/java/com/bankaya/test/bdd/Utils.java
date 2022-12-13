package com.bankaya.test.bdd;

import com.bankaya.pokemon.entity.Ability;
import com.bankaya.pokemon.entity.Pokemon;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static Pokemon newPokemonWithAbilities(String pokemonName){
        return  new Pokemon(150L,pokemonName,makeRandomAbilities());
    }

    private static List<Ability> makeRandomAbilities() {
        return Arrays.asList(new Ability("",Boolean.FALSE,10)
                            ,new Ability("",Boolean.TRUE,1));
    }
}
