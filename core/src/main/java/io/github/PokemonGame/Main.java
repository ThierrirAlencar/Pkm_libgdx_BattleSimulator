package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;
import sun.tools.jconsole.JConsole;

import static jdk.internal.org.jline.terminal.spi.SystemStream.Input;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private ApplicationAdapter currentScene; // Cena ativa

    @Override
    public void create() {
        // Inicializa com uma cena inicial
        setScene(new Game());
    }

    @Override
    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    @Override
    public void dispose() {
        if (currentScene != null) {
            currentScene.dispose();
        }
    }

    // Método para alternar entre cenas
    public void setScene(ApplicationAdapter newScene) {
        if (currentScene != null) {
            currentScene.dispose(); // Libera recursos da cena anterior
        }
        currentScene = newScene;
        if (currentScene != null) {
            currentScene.create(); // Inicializa a nova cena
        }
    }
}
