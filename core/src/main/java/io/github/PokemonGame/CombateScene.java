package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.gl;

public class CombateScene extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private PerspectiveCamera camera;
    private Texture BackgroundTexture;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;

    public PerspectiveCamera Camera;
    //Create event happens when the class is created
    @Override
    public void create(){
        //initialize camera and viewport
        Camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Camera.position.set(0, 0, 0); // Set the camera position
        Camera.lookAt(0, 0, 0); // Look at the origin
        Camera.near = 1f; // Set the near clipping plane
        Camera.far = 100f; // Set the far clipping plane
        Camera.update(); // Update the camera after setting properties

        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Camera);

        Gdx.app.log("Cena1", "Cena 1 carregada!");
        //create a sprite batch
        spriteBatch = new SpriteBatch();

        //Create a Stage
        stage = new Stage(viewport,spriteBatch);
        //Set the input processor to be Stage
        Gdx.input.setInputProcessor(stage);

        //Set up the table
        // table is an easy way to manage the actors
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        //Add table inside the scene
        stage.addActor(mainTable);

        BackgroundTexture = new Texture("Ui/battle.png");


    }

    @Override
    public void render(){
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //update the camera position
        stage.getCamera().update();


        //get viewport
        stage.getViewport().apply();
        //apply the viewport


        //get the sprite batch
        Batch batch = stage.getBatch();
        //begin drawing area
        batch.begin();
        //Stage batch

        //Draw the background texture
        float worldWidth = viewport.getWorldWidth();
        float worldHeiht = viewport.getWorldHeight();
        spriteBatch.draw(BackgroundTexture,0,0,worldWidth,worldHeiht);

        batch.end();


        stage.act(); //get actors working properly

    }

    @Override
    public void dispose(){
        stage.dispose();
        BackgroundTexture.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width,height,true);
        stage.getCamera().viewportWidth = width;
        stage.getCamera().viewportHeight = height;
        stage.getCamera().update();

        // Update the viewport and the camera's viewport size
        viewport.update(width, height, true);
        Camera.viewportWidth = width;
        Camera.viewportHeight = height;
        Camera.update(); // Recalculate the camera matrices
    }
}
