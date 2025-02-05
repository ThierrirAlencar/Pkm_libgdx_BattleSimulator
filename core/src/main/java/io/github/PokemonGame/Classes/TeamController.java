package io.github.PokemonGame.Classes;

import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Exceptions.TheresNoSelectedPokemon;

import java.util.ArrayList;

public class TeamController {
    private ArrayList<Pokemon> team = new ArrayList<Pokemon>();
    private Pokemon CurrentPokemon;

    public ArrayList<Pokemon> getTeam() {
        return team;
    }

    public Pokemon getCurrentPokemon(){
        return CurrentPokemon;
    }
    public Pokemon getNextPokemon(){
        for(int i=0; i<team.size();i++){
            if(team.get(i).getLife()>0){
                return team.get(i);
            }
        }

        return team.get(0);
    }
    public boolean TheresAnyoneAlive(){
        for(int i=0; i<team.size();i++){
            if(team.get(i).getLife()>0){
                return true;
            }
        }

        return  false;
    }
    public void SetCurrentPokemon(int Index) throws TheresNoSelectedPokemon{
        if(team.get(Index) !=null && team.get(Index) != CurrentPokemon){
            CurrentPokemon = team.get(Index);
        }else {
            throw new TheresNoSelectedPokemon("The selected pokemon is null");
        }
    }
    public void AddToTeam(Pokemon pokemon){
        if(team.size()<6){ //Pode-se ter no máximo 6 pokemons
            team.add(pokemon);
            CurrentPokemon = pokemon;
        }
    }
}
