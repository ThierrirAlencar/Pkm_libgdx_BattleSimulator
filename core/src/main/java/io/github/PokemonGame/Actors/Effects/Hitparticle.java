package io.github.PokemonGame.Actors.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.PokemonGame.Actors.Actor;

public class Hitparticle extends Actor {
    private ParticleEffect particle;

    public Hitparticle(){
        particle  = new ParticleEffect();
    }

    public void drawParticle(SpriteBatch batch){}

}
