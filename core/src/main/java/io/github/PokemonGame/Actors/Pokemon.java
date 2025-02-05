package io.github.PokemonGame.Actors;

import com.badlogic.gdx.graphics.Texture;
import io.github.PokemonGame.Types.TYPES;
import io.github.PokemonGame.Classes.move;

import java.util.ArrayList;

public class Pokemon extends Actor{
    private String name;
    private int Life;
    private int cIndex;
    private Texture frontTexture;
    private Texture backTexture;
    private int MaxLife = 100;

    private ArrayList<move> moves = new ArrayList<move>(4);
    private TYPES type;
    public Pokemon(int cIndex,TYPES types, String name,int exp,int maxLife, ArrayList<move> atks) {
        this.cIndex = cIndex;
        Life = maxLife;
        this.name = name;
        this.MaxLife = maxLife;
        frontTexture = new Texture("pokemons/Front/"+this.cIndex+".png");
        backTexture = new Texture("pokemons/Back/"+this.cIndex+".png");
        this.type = types;
        this.moves = atks;
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
    public void Damage(float damage){
        this.Life -= damage;
    }
    public void disposeTexture(){
        frontTexture.dispose();
        backTexture.dispose();
    }
    public TYPES getType() {
        return type;
    }

    public int getMaxLife() {
        return MaxLife;
    }

    public void setMaxLife(int maxLife) {
        MaxLife = maxLife;
    }
    public ArrayList<move> getMoves(){
        return moves;
    }
}
