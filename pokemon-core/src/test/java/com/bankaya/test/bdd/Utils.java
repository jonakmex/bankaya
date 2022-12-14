package com.bankaya.test.bdd;

import com.bankaya.pokemon.entity.Ability;
import com.bankaya.pokemon.entity.Pokemon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
    public static Pokemon newPokemonWithAbilities(String pokemonName){
        return  new Pokemon((new Random().nextLong()),pokemonName,makeRandomAbilities(),(new Random().nextInt()));
    }

    private static List<Ability> makeRandomAbilities() {
        return Arrays.asList(new Ability("ability a",Boolean.FALSE,10)
                            ,new Ability("ability b",Boolean.TRUE,1));
    }
}
