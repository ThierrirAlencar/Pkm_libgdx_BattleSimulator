package io.github.PokemonGame.Classes;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.TYPES;

import java.util.ArrayList;

public class PokedexController {
    private ArrayList<Pokemon> Dex = new ArrayList<Pokemon>();
    public int index = 1;
    public PokedexController(){
        Generators gen = new Generators();
        //Should come from database later;
        Dex.add(new Pokemon(1,TYPES.PLANT, "Bulbasaur", 0, 45,gen.genMoveList(TYPES.PLANT)));
        Dex.add(new Pokemon(2,TYPES.PLANT, "Ivysaur", 0, 60,gen.genMoveList(TYPES.PLANT)));
        Dex.add(new Pokemon(3,TYPES.PLANT, "Venusaur", 0, 80,gen.genMoveList(TYPES.PLANT)));
        Dex.add(new Pokemon(4,TYPES.FIRE, "Charmander", 0, 39,gen.genMoveList(TYPES.FIRE)));
        Dex.add(new Pokemon(5,TYPES.FIRE, "Charmeleon", 0, 58,gen.genMoveList(TYPES.FIRE)));
        Dex.add(new Pokemon(6,TYPES.FIRE, "Charizard", 0, 78,gen.genMoveList(TYPES.FIRE)));
        Dex.add(new Pokemon(7,TYPES.WATER, "Squirtle", 0, 44,gen.genMoveList(TYPES.WATER)));
        Dex.add(new Pokemon(8,TYPES.WATER, "Wartortle", 0, 59,gen.genMoveList(TYPES.WATER)));
        Dex.add(new Pokemon(9,TYPES.WATER, "Blastoise", 0, 79,gen.genMoveList(TYPES.WATER)));
        Dex.add(new Pokemon(10,TYPES.BUG, "Caterpie", 0, 45,gen.genMoveList(TYPES.BUG)));
        Dex.add(new Pokemon(11,TYPES.BUG, "Metapod", 0, 50,gen.genMoveList(TYPES.BUG)));
        Dex.add(new Pokemon(12,TYPES.FLYING, "Butterfree", 0, 60,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(13,TYPES.BUG, "Weedle", 0, 40,gen.genMoveList(TYPES.BUG)));
        Dex.add(new Pokemon(14,TYPES.BUG, "Kakuna", 0, 45,gen.genMoveList(TYPES.BUG)));
        Dex.add(new Pokemon(15,TYPES.FLYING, "Beedrill", 0, 65,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(16,TYPES.FLYING, "Pidgey", 0, 40,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(17,TYPES.FLYING, "Pidgeotto", 0, 63,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(18,TYPES.FLYING, "Pidgeot", 0, 83,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(19,TYPES.NORMAL, "Rattata", 0, 30,gen.genMoveList(TYPES.NORMAL)));
        Dex.add(new Pokemon(20,TYPES.NORMAL, "Raticate", 0, 55,gen.genMoveList(TYPES.NORMAL)));
        Dex.add(new Pokemon(21,TYPES.FLYING, "Spearow", 0, 40,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(22,TYPES.FLYING, "Fearow", 0, 65,gen.genMoveList(TYPES.FLYING)));
        Dex.add(new Pokemon(23,TYPES.POISON, "Ekans", 0, 35,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(24,TYPES.POISON, "Arbok", 0, 60,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(25,TYPES.ELETRIC, "Pikachu", 0, 35,gen.genMoveList(TYPES.ELETRIC)));
        Dex.add(new Pokemon(26,TYPES.ELETRIC, "Raichu", 0, 60,gen.genMoveList(TYPES.ELETRIC)));
        Dex.add(new Pokemon(27,TYPES.GROUND, "Sandshrew", 0, 50,gen.genMoveList(TYPES.GROUND)));
        Dex.add(new Pokemon(28,TYPES.GROUND, "Sandslash", 0, 75,gen.genMoveList(TYPES.GROUND)));
        Dex.add(new Pokemon(29,TYPES.GROUND, "Nidoran♀", 0, 55,gen.genMoveList(TYPES.GROUND)));
        Dex.add(new Pokemon(30,TYPES.POISON, "Nidorina", 0, 70,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(31,TYPES.POISON, "Nidoqueen", 0, 90,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(32,TYPES.GROUND, "Nidoran♂", 0, 46,gen.genMoveList(TYPES.GROUND)));
        Dex.add(new Pokemon(33,TYPES.POISON, "Nidorino", 0, 61,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(34,TYPES.POISON, "Nidoking", 0, 81,gen.genMoveList(TYPES.POISON)));
        Dex.add(new Pokemon(35,TYPES.FAIRY, "Clefairy", 0, 70,gen.genMoveList(TYPES.FAIRY)));
    }

    public Pokemon getPkm(int i){
        // i -1 é pelo fato de que a lista começa a partir do zero e os pokemons a partir do 1. então se eu peço o pokemon 3 ele me retornará o 4 o que resulta em problemas
        return Dex.get(i-1);
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
