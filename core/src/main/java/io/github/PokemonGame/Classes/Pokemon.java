package io.github.PokemonGame.Classes;

import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;
import com.badlogic.gdx.graphics.Texture;
public class Pokemon {
    private String name;
    private int Life;
    private String Texture;
    private int cIndex;
    private Texture texture;
    public Pokemon(int cIndex, int life, String name,Texture texture) {
        this.cIndex = cIndex;
        Life = life;
        this.name = name;
        this.texture = texture;
    }

    public void setTexture(com.badlogic.gdx.graphics.Texture texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return Life;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getcIndex() {
        return cIndex;
    }
    public void Damage(int damage){
        this.Life -= damage;
    }
}
