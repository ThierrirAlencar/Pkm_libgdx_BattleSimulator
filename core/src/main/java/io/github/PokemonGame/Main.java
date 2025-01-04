package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.PokemonGame.Stages.ChooseTeamScene;
import io.github.PokemonGame.Stages.CombatStage;
import io.github.PokemonGame.Stages.WorldRenderStage;
import io.github.PokemonGame.Stages.WorldStage;

import static com.badlogic.gdx.Gdx.gl;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private ApplicationAdapter currentScene; // Cena ativa
    private Table table;
    //viewport
    private FitViewport viewport;
    //stage
    public Stage stage;
    //Camera
    public OrthographicCamera Camera;
    public SpriteBatch batch;

    //textures
    public Texture BgTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Camera.position.set(0, 0, -1); // Set camera position
        Camera.update();
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Camera);
        stage = new Stage(viewport,batch);

        Skin textSkin = new Skin(Gdx.files.internal("Ui/BattleCore/Buttons/CustomUITextButton.json"));

        Table table = new Table();

        BgTexture = new Texture(Gdx.files.internal("banner.png"));
        TextButton btn1 = new TextButton("Demo de luta",textSkin);
        TextButton btn2 = new TextButton("Demo de Andar",textSkin);
        TextButton btn3 = new TextButton("Demo de Banco de dados",textSkin);

        stage.addActor(table);
        table.setPosition(Gdx.graphics.getWidth()/2,(Gdx.graphics.getHeight()/2)-64);
        table.add(btn1).width(150).height(75).pad(15);table.row();
        table.add(btn2).width(150).height(75).pad(15);table.row();
        table.add(btn3).width(150).height(75).pad(15);table.row();

        btn1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScene(new CombatStage());
                Gdx.app.log("Scene changed","Changed Scene because button one called");
            }
        });
        btn2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScene(new WorldRenderStage());
                Gdx.app.log("Scene changed","Changed Scene because button two called");
            }
        });
        btn3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setScene(new ChooseTeamScene());
                Gdx.app.log("Scene changed","Changed Scene because button three called");
            }
        });
        //define stage como processador de inputs (para comandos futuros)
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        // Clear the screen
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background using SpriteBatch
        Camera.update();
        batch.setProjectionMatrix(Camera.combined);

        batch.begin();
        batch.draw(BgTexture,0,0,Camera.viewportWidth,Camera.viewportHeight);
        batch.end();

        stage.act();
        stage.draw();
        if (currentScene != null) {
            currentScene.render();
        }
    }

    @Override
    public void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
        batch.dispose();
        stage.dispose();
    }

    // Método para alternar entre cenas
    public void setScene(ApplicationAdapter newScene) {
        Gdx.input.setInputProcessor(null); //Limpa o processador de inputs para que um novo possa ser chamado na cena
        if (currentScene != null) {
            currentScene.dispose(); // Libera recursos da cena anterior
        }
        if(newScene != null){
            Gdx.app.log("Changes Scene",newScene.toString());
            currentScene = newScene;
        }else {
            this.create();
        }

        if (currentScene != null) {
            currentScene.create(); // Inicializa a nova cena
        }
    }
    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }
    public Stage getStage() {
        return stage;
    }
}
