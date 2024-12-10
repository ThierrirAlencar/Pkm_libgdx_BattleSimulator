package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;

import static com.badlogic.gdx.Gdx.gl;

public class Game extends ApplicationAdapter {
    //Isso é uma textura(basicamente um sprite)
    public Texture ArenaTexture;
    public Texture yourPokemom;
    public Texture EnemyPokemom;
    //Area de desenho de texturas
    public SpriteBatch batch;
    //stage
    public Stage stage;
    //Camera
    public PerspectiveCamera Camera;
    public int x = 0;

    //viewport
    private FitViewport viewport;

    //Create event happens when the class is created
    @Override
    public void create(){
        batch = new SpriteBatch();
        //Load UI


        //Load Pokemom textures
        yourPokemom = new Texture("pokemons/Back/Charizard.png");
        EnemyPokemom = new Texture("pokemons/Front/Venussaur.png");


        //Load another sprites
        ArenaTexture = new Texture("Ui/battle.png");

        //set viewport and camera
        Camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Camera.position.set(0, 0, 10); // Set camera position
        Camera.lookAt(0, 0, 0); // Ensure the camera is looking at the origin
        Camera.near = 1f;
        Camera.far = 100f;
        Camera.update();

        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Camera);

        //Skin

        Skin skin = new Skin(Gdx.files.internal("Ui/buttonStyle.json"));

        //Scenes management
        stage = new Stage(viewport,batch);

        Table table = new Table();
        table.setFillParent(true); //fills the entire layout
        stage.addActor(table); // add the table to the scene

        //Creates the buttons
        TextButton button = new TextButton("Ataque 1",skin);
        TextButton button2 = new TextButton("Ataque 2",skin);
        table.add(button).expand().center().width(200).height(200);

        //define stage como processador de inputs (para comandos futuros)
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(){
        input();
        logic();
        draw();
    }

    //where we will read players commands
    public void input(){

    }

    public void logic(){

    }

    public void draw(){
        // Clear the screen
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background using SpriteBatch
        Camera.update();

        //start draw zone
        batch.begin();
        float worldWidth = Camera.viewportWidth;
        float worldHeiht = Camera.viewportHeight;
        batch.draw(ArenaTexture, 0, 0, Camera.viewportWidth, Camera.viewportHeight);
        batch.draw(yourPokemom,25,-12,200,200);
        batch.draw(EnemyPokemom,380,165,200,200);
        //end draw zone
        batch.end();
        //Draw the Ui using the stage
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }
    @Override
    public void dispose(){
        ArenaTexture.dispose();
        yourPokemom.dispose();
        EnemyPokemom.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true); // true centers the camera
    }

}
