package io.github.PokemonGame.Classes.Battle;

import com.badlogic.gdx.Gdx;
import io.github.PokemonGame.Actors.Pokemon;

import java.util.Random;


//Criando a classe Batalha de herda de Pokemon

public class Batalha {
    private Pokemon adversário;
    private Pokemon jogador;

    public Batalha(Pokemon adversário, Pokemon jogador) {
	        this.adversário = adversário;
            this.jogador = jogador;
     }
	public void atacarOponente(int escolha) {
        Gdx.app.log("Turn","Choose move "+this.jogador.getMoves().get(escolha).name+" to attack "+this.adversário.getName());
	        //int escolha; - remover a escolha pq está será feita a partir da interface
	        if(this.jogador.getLife()>0){
                //recebe um ataque do jogador e ataca um adversário
                this.jogador.getMoves().get(escolha).atack(this.adversário);
            }
            if(this.adversário.getLife()>0){
                //Realiza um ataque aleatório contra o jogador
                Random rand = new Random();
                int move = rand.nextInt(3)+1;
                Gdx.app.log("Turn",this.adversário.getName() + " choose move "+this.adversário.getMoves().get(move).name+" to attack "+this.jogador.getName());this.adversário.getMoves().get(move);
                this.adversário.getMoves().get(move).atack(this.jogador);
            }
            return;
    }

    //trocar pokemon
    public void trocarPokemon(Pokemon pokemon){
        this.jogador = pokemon;
    }
    //trocar adversario
    public void trocarAdversário(Pokemon pokemon){
        this.jogador = pokemon;
    }
}
