package io.github.PokemonGame.Stages;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.PokemonGame.Actors.Player;
import io.github.PokemonGame.Main;

public class WorldRenderStage extends ApplicationAdapter {
    // SpriteBatch
    private SpriteBatch batch;

    // Camera
    private OrthographicCamera camera;

    // Viewport
    private StretchViewport viewport;

    //actors
    private Player player;

    // Stage
    private Stage stage;

    // Map render
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmxRender;

    // Constants
    private static final int TILE_SIZE = 32;
    private static final int VISIBLE_TILES_X = 25;
    private static final int VISIBLE_TILES_Y = 18;

    @Override
    public void create() {
        Gdx.app.log("Stage called", "render world demonstration");

        //Calculos muito fodas ae
        float viewportWidth = TILE_SIZE * VISIBLE_TILES_X;
        float viewportHeight = TILE_SIZE * VISIBLE_TILES_Y;

        player = new Player(new Texture("player/playerFront.png"),12*TILE_SIZE,31*TILE_SIZE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight); // Set camera size
        camera.position.set(0, 0, 0); // Center camera
        camera.update();


        map = new TmxMapLoader().load("Map/teste.tmx");


        tmxRender = new OrthogonalTiledMapRenderer(map);


        batch = new SpriteBatch();


        viewport = new StretchViewport(viewportWidth, viewportHeight, camera);


        stage = new Stage(viewport, batch);
    }

    @Override
    public void render() {
        draw();
        input();
    }
    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        tmxRender.setView(camera);
        tmxRender.render();

        batch.begin();
        batch.draw(player.playerTexture,player.x,player.y);
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

        camera.position.set(player.x,player.y,10);
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        tmxRender.dispose();
    }
}
