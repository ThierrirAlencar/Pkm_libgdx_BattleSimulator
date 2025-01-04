package io.github.PokemonGame.Maps;

import io.github.PokemonGame.Types.TERRAIN;

//Cada "quadradinho" dentro do nosso jogo é um tile. Cada um deles será representado por essa classe
public class Tile {
    private TERRAIN terrain;
    private Boolean collision = false; //Observa se o tile possuí colisão

    public Tile(TERRAIN terrain){
        this.terrain = terrain;
    }

    public TERRAIN getTerrain(){
        return this.terrain;
    }
}
