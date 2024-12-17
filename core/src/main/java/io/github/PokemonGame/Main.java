package io.github.PokemonGame;

import com.badlogic.gdx.ApplicationAdapter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private ApplicationAdapter currentScene; // Cena ativa

    @Override
    public void create() {
        // Inicializa com uma cena inicial
        setScene(new CombatScene());
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
