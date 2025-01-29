package io.github.PokemonGame.Classes.Battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import io.github.PokemonGame.Actors.Pokemon;

import java.util.Random;


//Criando a classe Batalha de herda de Pokemon

public class Batalha {
    private Pokemon adversário;
    private Pokemon jogador;
    private String turno = "noTurn";
    public Batalha(Pokemon adversário, Pokemon jogador) {
	        this.adversário = adversário;
            this.jogador = jogador;
     }
    public void atacarOponente(int escolha) {
        Gdx.app.log("Turn", "Choose move " + this.jogador.getMoves().get(escolha).name + " to attack " + this.adversário.getName());
        turno = "player";
        float delayTime = 1.5f; // atraso em segundos
        if (this.jogador.getLife() > 0) {
            this.jogador.getMoves().get(escolha).atack(this.adversário);
        }

        // Adicionando um atraso antes da ação do oponente
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                turno = "oponente";
                Gdx.app.log("End turn", "Enemy turn begins");

                if (adversário.getLife() > 0) {
                    Random rand = new Random();
                    int move = rand.nextInt(3); // Ajustado para evitar erro de índice
                    Gdx.app.log("Turn", adversário.getName() + " choose move " + adversário.getMoves().get(move).name + " to attack " + jogador.getName());
                    adversário.getMoves().get(move).atack(jogador);
                }

                // Após a ação do oponente, volta para o turno do jogador com outro atraso
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        turno = "noTurn";
                        Gdx.app.log("End turn", "No turn in the end. Waiting for player's interaction");
                    }
                }, delayTime); // Atraso entre turnos
            }
        }, delayTime); // Atraso após o ataque do jogador


    }

    //trocar pokemon
    public void trocarPokemon(Pokemon pokemon){
        this.jogador = pokemon;
    }
    //trocar adversario
    public void trocarAdversário(Pokemon pokemon){
        this.jogador = pokemon;
    }

    public String getTurno(){
        return turno;
    }
}
