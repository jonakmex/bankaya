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
        return Arrays.asList(new Ability(7L,"limber")
                            ,new Ability(150L,"imposter"));
    }
}
