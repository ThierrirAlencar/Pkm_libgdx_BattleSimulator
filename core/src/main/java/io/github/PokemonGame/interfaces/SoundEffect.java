package io.github.PokemonGame.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
    private Sound sound;

    public SoundEffect(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public long playSound(float vol){
        return this.sound.play(vol);
    }
}
