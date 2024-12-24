package io.github.PokemonGame.Classes;

import com.badlogic.gdx.graphics.Texture;
import io.github.PokemonGame.Actors.Pokemon;

import java.util.ArrayList;
import java.util.Random;

public class Generators {
    private ArrayList<String> PkmNames = new ArrayList<String>();
    public Generators(){
        this.PkmNames.add("0");
        this.PkmNames.add("Bulbassaur");this.PkmNames.add("Ivysaur");this.PkmNames.add("Venussaur");this.PkmNames.add("Charmander");
        this.PkmNames.add("Charmeleon");this.PkmNames.add("Charizard");this.PkmNames.add("Squirtle");
        this.PkmNames.add("Wartortle");this.PkmNames.add("Blastoise");this.PkmNames.add("Catepie");
        this.PkmNames.add("Metapod");this.PkmNames.add("Butterfree");this.PkmNames.add("Weedle");this.PkmNames.add("Kakuna");
    }
    public Pokemon GenPkm(Boolean Oposit){
        //Gera um pokemon aleatorio
        Random rand = new Random();

        Texture texture;
        int cIndex = rand.nextInt(12)+1;
        if(Oposit){
            texture = new Texture("pokemons/Front/"+cIndex+".png");
        }else {
            texture = new Texture("pokemons/Back/"+cIndex+".png");
        }

        return  new Pokemon(cIndex,100,PkmNames.get(cIndex),texture,0,100);
    }
}
