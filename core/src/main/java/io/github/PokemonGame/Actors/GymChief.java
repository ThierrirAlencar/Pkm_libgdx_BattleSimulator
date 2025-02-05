package io.github.PokemonGame.Actors;

import com.badlogic.gdx.graphics.Texture;

public class GymChief extends Actor{
    public int x;
    public int y;
    public Texture currentTexture = new Texture("Map/Captain_NPC.png");

    public GymChief(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
