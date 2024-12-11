package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
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
    public OrthographicCamera Camera;
    public int x = 0;

    //viewport
    private FitViewport viewport;
    private Table table = new Table();
    //pkm states Later throw this into a specific class
    public int OpositLife = 100;
    public double PersonalLife = 100;



    //Create event happens when the class is created
    @Override
    public void create(){
        batch = new SpriteBatch();
        //Load UI


        //Load Pokemom textures
        yourPokemom = new Texture("pokemons/Back/Venussaur.png");
        EnemyPokemom = new Texture("pokemons/Front/Charizard.png");


        //Load another sprites
        ArenaTexture = new Texture("Ui/battle.png");

        //set viewport and camera
        Camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Camera.position.set(0, 0, 10); // Set camera position

        Camera.update();

        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Camera);

        //Skin

        Skin skin = new Skin(Gdx.files.internal("Ui/buttonStyle.json"));
        Skin textSkin = new Skin(Gdx.files.internal("Ui/widgets_ui.json"));
        BitmapFont font = textSkin.getFont("my_font");
        font.getData().setScale(0.5f);
        //Scenes management
        stage = new Stage(viewport,batch);


        table.setFillParent(true); //fills the entire layout


        stage.addActor(table); // add the table to the scene

        textSkin.setScale(10f);

        //Creates the buttons
        TextButton button = new TextButton("Ataque 1",textSkin);
        TextButton button2 = new TextButton("Ataque 2",textSkin);
        TextButton button3 = new TextButton("Ataque 3",textSkin);
        TextButton button4 = new TextButton("Ataque 4",textSkin);

        //Define a table para adicionar os botões na tela
        table.setSize(400,300);
        table.right().bottom();
        //table.background("Ui/BattleCore/Bordas.png");
        //table.debug();
        table.add(button).width(150).height(75).pad(15);
        table.add(button2).width(150).height(75).pad(15);
        table.row();
        table.add(button3).width(150).height(75).pad(15);
        table.add(button4).width(150).height(75).pad(15);

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
        // width = currentHealth / totalHealth * totalBarWidth;

    }


    public void draw(){


        // Clear the screen
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background using SpriteBatch
        Camera.update();
        batch.setProjectionMatrix(Camera.combined);
        //start draw zone
        batch.begin();
        batch.draw(ArenaTexture, 0, 0, Camera.viewportWidth, Camera.viewportHeight);
        batch.draw(yourPokemom,100,0,300,300);
        batch.draw(EnemyPokemom,600,300,300,300);
        //end draw zone
        batch.end();


        // Draw the stage (UI)
        stage.act();
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
