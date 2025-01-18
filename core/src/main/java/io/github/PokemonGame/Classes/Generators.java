package io.github.PokemonGame.Classes;

import com.badlogic.gdx.graphics.Texture;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.TYPES;
import io.github.PokemonGame.interfaces.move;

import java.util.ArrayList;
import java.util.Random;

public class Generators {

    public Pokemon GenPkm(Boolean Oposit){
        //Gera um pokemon aleatorio
        PokedexController pDex = new PokedexController();
        ArrayList<Pokemon> Dex = pDex.getDex();
        Random rand = new Random();
        int cIndex = rand.nextInt(Dex.size()-1)+1;
        return  Dex.get(cIndex);
    }

    public ArrayList<move> genMoveList(TYPES pkmType){
        ArrayList<move> list = new ArrayList<>(4);
        Random rand = new Random();
        for(int i = 0; i<4; i++){
            list.add(new move(rand.nextInt(20),pkmType,pkmType +"-"+ rand.nextInt(100)));
        }
        return list;
    }
}
