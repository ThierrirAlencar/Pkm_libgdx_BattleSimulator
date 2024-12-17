package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;

import java.util.Random;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.math.MathUtils.random;

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
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    //viewport
    private FitViewport viewport;
    private Table table = new Table();
    private Table SecondOptionsTable = new Table();
    private Boolean showSecondTable = false;
    //pkm states Later throw this into a specific class
    public float OpositLife = 80;
    public float PersonalLife = 100;
    public int currentPokemon = 3;
    public int currentEnemyPkm = 6;
    public Skin textSkin = new Skin(Gdx.files.internal("Ui/ui_skin.json"));
    public BitmapFont font = textSkin.getFont("font");

    //Create event happens when the class is created
    @Override
    public void create(){
        batch = new SpriteBatch();
        //Load UI


        //Load Pokemom textures
        yourPokemom = new Texture("pokemons/Back/"+currentPokemon+".png");
        EnemyPokemom = new Texture("pokemons/Front/"+currentEnemyPkm+".png");
        //Load progress bar
        ShapeRenderer bar = new ShapeRenderer();

        //Load another sprites
        ArenaTexture = new Texture("Ui/BattleCore/battle.png");

        //set viewport and camera
        Camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Camera.position.set(0, 0, 10); // Set camera position

        Camera.update();

        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Camera);

        //Skin
        Skin skin = new Skin(Gdx.files.internal("Ui/buttonStyle.json"));

        font.getData().setScale(0.5f);
        //Scenes management
        stage = new Stage(viewport,batch);


        table.setFillParent(true); //fills the entire layout


        stage.addActor(table); // add the table to the scene

        textSkin.setScale(10f);

        //Creates the buttons
        TextButton button = new TextButton("Atacar",textSkin);
        TextButton button2 = new TextButton("Pokemon",textSkin);
        TextButton button3 = new TextButton("Bolsa",textSkin);
        TextButton button4 = new TextButton("Fugir",textSkin);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                turnTrue();
            }
        });
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                SwitchPokemon();
            }
        });


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
    public void turnTrue(){
        this.OpositLife-=10;
    }
    public void SwitchPokemon(){
        switch (this.currentPokemon){
            case 3:this.currentPokemon = 6; break;
            case 6:this.currentPokemon = 3; break;
        }
        yourPokemom.dispose();
        yourPokemom = new Texture("pokemons/Back/"+currentPokemon+".png");
    }
    //where we will read players commands
    public void input(){

    }
    public void YourPokemonIsDead(){
        SwitchPokemon();
    }
    public void EnemyPokemonIsDead(){
        Random random = new Random();
        currentEnemyPkm = random.nextInt(12) + 1;
        EnemyPokemom.dispose();
        EnemyPokemom = new Texture("pokemons/Front/"+currentEnemyPkm+".png");
    }
    public void logic(){
        if(PersonalLife<=0){
            YourPokemonIsDead();
        }
        if(OpositLife<=0){
            EnemyPokemonIsDead();
            OpositLife = 100;
        }
    }

    public void drawHealthBar(){
        //é meio auto explicativo (desenha a barra de vida dos dois pokemons)
        // Essas variaveis definem o tamanho da barra de vida que é proporcional a vida
        // width = currentHealth / totalHealth * totalBarWidth;
        float width = (PersonalLife / 100f )*300f;
        float OpositWidth = (OpositLife / 100f * 300f);
        float experience = 20;
        // Desenha a barra de vida preenchida (em verde)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Barra de vida
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(720, 230, width, 10);

        //Barra de experiencia
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(920, 210, experience*5, 5);

        //Barra de vida do adversário
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(50, 700,OpositWidth,10);
        shapeRenderer.end();

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
        //Nome do pokemon
        font.setColor(Color.BLACK);
        font.draw(batch,"Venussaur",720,280,1.2f,10,false);
        font.draw(batch,"Charizard",50, 750,1.2f,10,false);
        //end draw zone
        batch.end();

        // Draw health bars
        drawHealthBar();


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
        stage.dispose();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true); // true centers the camera
    }

}
