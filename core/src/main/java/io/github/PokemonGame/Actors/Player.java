package io.github.PokemonGame.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.PokemonGame.Main;
import io.github.PokemonGame.Stages.CombatStage;

public class Player extends Actor {
    //Textures
    public Texture playerTexture;
    public Texture FrontAnim = new Texture("player/walkingAnimFront.png");
    public Texture BackAnim = new Texture("player/walkingAnimBack.png");
    public Texture LeftAnim = new Texture("player/walkingAnimLeft.png");
    public Texture RigthAnim = new Texture("player/walkingAnimRight.png");
    public TextureRegion[] animationFrames;


    public Animation frontAnimation;
    public Animation backAnimation;
    public Animation LeftAnimation;
    public Animation rightAnimation;

    public Animation currentAnimation = new Animation<TextureRegion>(1f/4f,new TextureRegion[2]);
    //Stats
    public int x;
    public int y;
    public int spd = 3;

    public boolean isRunning = false;

    public Player(Texture playerTexture,int x, int y) {
        this.playerTexture = playerTexture;
        this.x = x;this.y = y;

        //Importa o sprite da textura e transforma em uma animção
        frontAnimation = SetUpAnimation(FrontAnim);
        backAnimation = SetUpAnimation(BackAnim);
        LeftAnimation = SetUpAnimation(LeftAnim);
        rightAnimation = SetUpAnimation(RigthAnim);
        currentAnimation = frontAnimation;
    }


    public Animation<TextureRegion> SetUpAnimation(Texture animTexture){
        TextureRegion[][] tmpFrames = TextureRegion.split(animTexture, 32, 48);
        int totalFrames = tmpFrames.length * tmpFrames[0].length;
        TextureRegion[] animationFrames = new TextureRegion[totalFrames];
        int index = 0;

        for (int i = 0; i < tmpFrames.length; i++) {
            for (int j = 0; j < tmpFrames[i].length; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        return new Animation<TextureRegion>(1f / 4f, animationFrames);
    }
    public void move(){
        isRunning = false;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = LeftAnimation;
            playerTexture = new Texture("player/playerLeft.png");
            x -= spd;
            isRunning = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            currentAnimation = rightAnimation;
            playerTexture = new Texture("player/playerRight.png");
            x += spd;
            isRunning = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentAnimation = backAnimation;
            playerTexture = new Texture("player/playerBack.png");
            y += spd;
            isRunning = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentAnimation = frontAnimation;
            playerTexture = new Texture("player/playerFront.png");
            y -= spd;
            isRunning = true;
        }
    }

}
