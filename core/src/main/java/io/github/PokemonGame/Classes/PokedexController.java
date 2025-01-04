package io.github.PokemonGame.Classes;

import com.badlogic.gdx.graphics.Texture;
import com.sun.org.apache.xerces.internal.xs.StringList;
import io.github.PokemonGame.Actors.Pokemon;

import java.util.ArrayList;

public class PokedexController {
    private ArrayList<Pokemon> Dex = new ArrayList<Pokemon>();
    public int index = 1;
    public PokedexController(){
        //Should come from database later;
        Dex.add(new Pokemon(1,270,"Bulbassaur",0,270));
        Dex.add(new Pokemon(2,270,"Ivyssaur",0,270));
        Dex.add(new Pokemon(3,270,"Venussaur",0,270));
        Dex.add(new Pokemon(4,70,"Charmander",0,70));
        Dex.add(new Pokemon(5,120,"Charmeleon",0,120));
        Dex.add(new Pokemon(6,150,"Charizard",0,100));
        Dex.add(new Pokemon(7,80,"Squirtle",0,80));
        Dex.add(new Pokemon(8,90,"Wartortle",0,90));
        Dex.add(new Pokemon(9,270,"Blastoise",0,270));
        Dex.add(new Pokemon(10,270,"Caterpie",0,270));
        Dex.add(new Pokemon(11,270,"Metapod",0,270));
        Dex.add(new Pokemon(12,270,"Buttlefree",0,270));
        Dex.add(new Pokemon(13,270,"Weedle",0,270));
        Dex.add(new Pokemon(14,270,"Kakuna",0,270));
    }

    public Pokemon getPkm(int i){
        return Dex.get(i);
    }
    public ArrayList<Pokemon> getDex(){
        return Dex;
    }
    //Retorna a seleção para o menu
    public ArrayList<Pokemon> getSelection(){
        ArrayList<Pokemon> list = new ArrayList<>();
        int size = Dex.size();
        if(index>0){
            // Add the previous Pokémon
            list.add(Dex.get(index - 1));
        }else{
            list.add(Dex.get(size-1));
        }

        // Add the selected Pokémon
        list.add(Dex.get(index));

        if(index<size-1){
            list.add(Dex.get(index+1));
        }else{
            list.add(Dex.get(0));
        }
        return list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
