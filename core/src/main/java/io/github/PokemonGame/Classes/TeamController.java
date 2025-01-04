package io.github.PokemonGame.Classes;

import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Exceptions.TheresNoSelectedPokemon;

import java.util.ArrayList;

public class TeamController {
    private ArrayList<Pokemon> team = new ArrayList<Pokemon>();
    private Pokemon CurrentPokemon;
    public TeamController() {
        CurrentPokemon = null;
        for(int i = 0; i<team.size();i++){
            team.add(null);
        }
    }
    public void SwitchCurrentPokemon(int Index){}
    public Pokemon getCurrentPokemon(){
        return CurrentPokemon;
    }
    public void SetCurrentPokemon(int Index) throws TheresNoSelectedPokemon{
        if(team.get(Index) !=null && team.get(Index) != CurrentPokemon){
            CurrentPokemon = team.get(Index);
        }else {
            throw new TheresNoSelectedPokemon("The selected pokemon is null");
        }
    }
    public void AddToTeam(Pokemon pokemon){
        for (int i =0; i<team.size();i++){
            if(team.get(i) == null){
                team.set(i,pokemon);
                i = team.size()+1;
            }
        }
    }
}
