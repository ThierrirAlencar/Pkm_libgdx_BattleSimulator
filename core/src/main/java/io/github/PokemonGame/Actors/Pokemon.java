package io.github.PokemonGame.Actors;

import com.badlogic.gdx.graphics.Texture;

public class Pokemon {
    private String name;
    private int Life;
    private String Texture;
    private int cIndex;
    private int exp=0;
    private Texture texture;
    private int MaxLife = 100;
    public Pokemon(int cIndex, int life, String name,Texture texture,int exp,int maxLife) {
        this.cIndex = cIndex;
        Life = life;
        this.name = name;
        this.texture = texture;
        this.exp = exp;
        this.MaxLife = maxLife;
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
    public void setExp(int exp){
        this.exp = exp;
    }
    public int getExp(){
        return exp;
    }
}
