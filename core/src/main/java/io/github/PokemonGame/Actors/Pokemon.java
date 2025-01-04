package io.github.PokemonGame.Actors;

import com.badlogic.gdx.graphics.Texture;

public class Pokemon {
    private String name;
    private int Life;
    private String Texture;
    private int cIndex;
    private int exp=0;
    private Texture frontTexture;
    private Texture backTexture;
    private int MaxLife = 100;
    public Pokemon(int cIndex, int life, String name,int exp,int maxLife) {
        this.cIndex = cIndex;
        Life = life;
        this.name = name;
        this.exp = exp;
        this.MaxLife = maxLife;
        frontTexture = new Texture("pokemons/Front/"+this.cIndex+".png");
        backTexture = new Texture("pokemons/Back/"+this.cIndex+".png");
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return Life;
    }

    public Texture getFrontTexture() {
        return frontTexture;
    }
    public Texture getBackTexture(){
        return backTexture;
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
    public void disposeTexture(){
        frontTexture.dispose();
        backTexture.dispose();
    }
}
