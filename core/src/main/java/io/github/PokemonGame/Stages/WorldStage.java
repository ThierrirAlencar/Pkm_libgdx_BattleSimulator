package io.github.PokemonGame.Stages;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.PokemonGame.Actors.Player;
import io.github.PokemonGame.Main;
import io.github.PokemonGame.Maps.TileMaps;

import static com.badlogic.gdx.Gdx.gl;

public class WorldStage extends ApplicationAdapter {
    //spritebatch
    private SpriteBatch batch = new SpriteBatch();

    //Camera
    private OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    //viewport
    private FitViewport viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
    //actors
    private Player player;
    //Scene
    private Stage stage = new Stage(viewport,batch);

    //Builder Class(Main)
    private Main builder;

    //TileMap
    private TileMaps map = new TileMaps(35,30);

    public WorldStage(Main builder) {
        this.builder = builder;
    }

    @Override
    public void create() {
        camera.position.set(0,0,10);

        player = new Player(new Texture("player/playerFront.png"),50,50);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    //método chamado a cada frame
    @Override
    public void render() {
        draw();
        logic();
        input();
    }

    //desenha as coisas na telad
    public void draw(){
        // Clear the screen
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        this.map.drawMap(batch);
        batch.begin();
        batch.draw(player.playerTexture,player.x,player.y,64,96);
        batch.end();
    }
    //recebe comandos do teclado
    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.playerTexture.dispose();
            player.playerTexture = new Texture("player/playerLeft.png");
            player.x-=player.spd;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.playerTexture.dispose();
            player.playerTexture = new Texture("player/playerRight.png");
            player.x+=player.spd;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.playerTexture.dispose();
            player.playerTexture = new Texture("player/playerBack.png");
            player.y+=player.spd;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player.playerTexture.dispose();
            player.playerTexture = new Texture("player/playerFront.png");
            player.y-=player.spd;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            new Main().setScene(new CombatStage());
        }
    }
    //faz a lógica do jogo
    public void logic(){

    }
    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        player.playerTexture.dispose();
    }


}
