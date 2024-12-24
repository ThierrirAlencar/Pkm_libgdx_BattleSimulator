package io.github.PokemonGame.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player {
    public Texture playerTexture;
    public int x;
    public int y;
    public Player(Texture playerTexture,int x, int y) {
        this.playerTexture = playerTexture;
        this.x = x;this.y = y;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }
}
