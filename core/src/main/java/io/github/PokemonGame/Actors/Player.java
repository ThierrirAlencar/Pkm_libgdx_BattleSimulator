package io.github.PokemonGame.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.PokemonGame.Main;
import io.github.PokemonGame.Stages.CombatStage;

public class Player extends Actor {
    //Textures
    public Texture currentTexture;
    public Texture FrontAnim = new Texture("player/walkingAnimFront.png");
    public Texture BackAnim = new Texture("player/walkingAnimBack.png");
    public Texture LeftAnim = new Texture("player/walkingAnimLeft.png");
    public Texture RigthAnim = new Texture("player/walkingAnimRight.png");
    public TextureRegion[] animationFrames;
    public Texture rTexture;
    public Texture lTexture;
    public Texture dTexture;
    public Texture tTexture;

    public Animation frontAnimation;
    public Animation backAnimation;
    public Animation LeftAnimation;
    public Animation rightAnimation;

    public Animation currentAnimation = new Animation<TextureRegion>(1f/4f,new TextureRegion[2]);

    //Stats
    public int spd = 450;

    //Corpo com colisõesp
    private Body playerBody;
    private World world;
    public boolean isRunning = false;

    public Player(Texture playerTexture,int x, int y,World world) {
        this.currentTexture = playerTexture;
        this.world = world;

        createPlayerBody(x, y);
        //Importa o sprite da textura e transforma em uma animção
        frontAnimation = SetUpAnimation(FrontAnim);
        backAnimation = SetUpAnimation(BackAnim);
        LeftAnimation = SetUpAnimation(LeftAnim);
        rightAnimation = SetUpAnimation(RigthAnim);
        currentAnimation = frontAnimation;

        rTexture = new Texture("player/playerRight.png");
        lTexture = new Texture("player/playerLeft.png");
        dTexture = new Texture("player/playerFront.png");;
        tTexture = new Texture("player/playerBack.png");;


    }


    private void createPlayerBody(float x, float y) {
        // Definição do corpo
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Corpo dinâmico
        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true; // Evita que o corpo rotacione ao colidir

        playerBody = world.createBody(bodyDef);

        // Criando a forma do jogador (exemplo: um retângulo de 32x32)
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32); // Metade do tamanho real (32x32)

        // Configuração da fixture (colisão)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;    // Define a massa do objeto
        fixtureDef.friction = 0f; // Define atrito (pode ser 0 para menos resistência)
        fixtureDef.restitution = 0f; // Define quique (0 = sem quique)

        // Criando a fixture
        playerBody.createFixture(fixtureDef);

        // Liberar a memória da shape após o uso
        shape.dispose();
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
        Vector2 velocity = new Vector2(0,0);

        //Gdx.app.log("Input", "Movendo jogador");

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = LeftAnimation;
            currentTexture = lTexture;
            velocity.x -= spd;
            isRunning = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            currentAnimation = rightAnimation;
            currentTexture = rTexture;
            velocity.x += spd;
            isRunning = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentAnimation = backAnimation;
            currentTexture =tTexture;
            velocity.y += spd;
            isRunning = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentAnimation = frontAnimation;
            currentTexture =dTexture;
            velocity.y -= spd;
            isRunning = true;
        }


        // Aplicando a velocidade ao corpo do jogador
        playerBody.setLinearVelocity(velocity);
    }

    public Body getPlayerBody() {
        return playerBody;
    }
}
