package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.PokemonGame.Classes.Pokemon;

import java.util.Random;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.math.MathUtils.random;

public class CombatScene extends ApplicationAdapter {
    //Isso é uma textura(basicamente um sprite)
    public Texture ArenaTexture;
    //Load Pokemom textures
    public Texture BackVenussaur = new Texture("pokemons/Back/3.png");
    public Texture FrontSquirtle = new Texture("pokemons/Front/7.png");
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
    public Pokemon currentPokemon = new Pokemon(3,100,"Venussaur",BackVenussaur);
    public Pokemon currentEnemyPkm = new Pokemon(7,60,"Squirtle",FrontSquirtle);
    public Skin textSkin = new Skin(Gdx.files.internal("Ui/ui_skin.json"));
    public BitmapFont font = textSkin.getFont("font");

    //Create event happens when the class is created
    @Override
    public void create(){
        batch = new SpriteBatch();


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
        this.currentEnemyPkm.Damage(10);
    }
    public void SwitchPokemon(){

        Texture BackVenussaur = new Texture("pokemons/Back/3.png");
        Texture BackCharizard = new Texture("pokemons/Back/6.png");
        switch (this.currentPokemon.getcIndex()){
            case 6:currentPokemon = new Pokemon(3,100,"Venussaur",BackVenussaur);Gdx.app.log("Turning","Venussaur"); break;
            case 3:currentPokemon = new Pokemon(6,150,"Charizard",BackCharizard);Gdx.app.log("Turning","Charizard");  break;
        }

        Texture cls = currentPokemon.getTexture();
        cls.dispose();
        currentPokemon.setTexture(new Texture("pokemons/Back/"+currentPokemon.getcIndex()+".png"));
    }
    //where we will read players commands
    public void input(){

    }
    public void YourPokemonIsDead(){
        SwitchPokemon();
    }
    public void EnemyPokemonIsDead(){
        Gdx.app.log("Enemy Pokemon is dead (Switching to a new enemy","Enemy Pokemon is dead (Switching to a new enemy");
        Random random = new Random();//Use it to create a random pokemon later
        currentEnemyPkm = new Pokemon(9,150,"Blastoise",new Texture("pokemons/Front/9.png"));
        currentEnemyPkm.getTexture().dispose();
        currentEnemyPkm.setTexture(new Texture("pokemons/Front/"+currentEnemyPkm.getcIndex()+".png"));
    }
    public void logic(){
        if(currentPokemon.getLife()<=0){
            YourPokemonIsDead();
        }
        if(currentEnemyPkm.getLife()<=0){
            EnemyPokemonIsDead();
        }
    }

    public void drawHealthBar(){
        //é meio auto explicativo (desenha a barra de vida dos dois pokemons)
        // Essas variaveis definem o tamanho da barra de vida que é proporcional a vida
        // width = currentHealth / totalHealth * totalBarWidth;
        float width = (currentPokemon.getLife() / 100f )*300f;
        float OpositWidth = (currentEnemyPkm.getLife() / 100f * 300f);
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
        batch.draw(currentPokemon.getTexture(),100,0,300,300);
        batch.draw(currentEnemyPkm.getTexture(),600,300,300,300);
        //Nome do pokemon
        font.setColor(Color.BLACK);
        font.draw(batch,currentPokemon.getName(),720,280,1.2f,10,false);
        font.draw(batch,currentEnemyPkm.getName(),50, 750,1.2f,10,false);
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
        currentEnemyPkm.getTexture().dispose();
        currentPokemon.getTexture().dispose();
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true); // true centers the camera
    }

}
