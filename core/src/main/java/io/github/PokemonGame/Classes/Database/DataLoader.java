package io.github.PokemonGame.Classes.Database;


import com.badlogic.gdx.Gdx;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.TYPES;
import io.github.PokemonGame.Classes.move;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataLoader {
	public static ArrayList<Pokemon> loadPokemons(Connection conn) throws SQLException {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String query = "SELECT p.id, p.nome, p.vida, t.tipo AS nome_tipo " +
            "FROM pokemon p " +
            "JOIN tipo_pokemon tp ON p.id = tp.idPokemon " +
            "JOIN tipos t ON tp.tipo1 = t.id;";
        // Aqui eu estou substituindo os valores de placeholders pelo nome das colunas do banco de dados""

        int pkmLimit = 151; //limite de pokemons adicionados
        int index = 1;
	    try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nome = rs.getString("nome");
	            int vida = rs.getInt("vida");
	            String nomeTipo = rs.getString("nome_tipo"); // Obtendo o nome do tipo


                TYPES tipoEnum = TYPES.valueOf(nomeTipo.toUpperCase());

                if(index<=pkmLimit){
                    // Alterado para passar o nome do tipo ao construtor

                    List<move> moveList = loadAtaques(conn); // Carrega lista de ataques
                    //filtrar lista de moves para retornar apenas alguns em especifico
                    List<move> filteredMoves = filtrar(moveList,tipoEnum);

                    Random rand = new Random();
                    ArrayList<move> finalMoves = new ArrayList<>();
                    for(int i = 0;i<4;i++){
                        if(filteredMoves.size()>i){ //Se o indice for maior que os ataques filtrados quer dizer que nao existem ataques suficientes
                            //sendo assim iremos puxar um ataque alatório
                            finalMoves.add(filteredMoves.get(rand.nextInt(filteredMoves.size())));
                            filteredMoves.remove(i);
                        }else {
                            finalMoves.add(moveList.get(rand.nextInt(moveList.size())));
                            moveList.remove(i);
                        }

                    }


                    Pokemon pokemon = new Pokemon(id,tipoEnum,nome,0,vida*2,finalMoves);

                    Gdx.app.log("Pokemon loaded",pokemon.getName());
                    pokemons.add(pokemon);
                }
                index++;

	        }
	    }
	    return pokemons; //Lista que será retornada com todos os pokemons
	}


    //Carrega uma lista de todos os tipos
	public static List<move> loadAtaques(Connection conn) throws SQLException {
	    List<move> ataques = new ArrayList<move>();
        String query = "SELECT a.id, a.nome, a.dano, a.effect, t.tipo AS nome_tipo " +
            "FROM ataque a " +
            "JOIN tipos t ON a.tipo = t.id;";

	    try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nome = rs.getString("nome");
	            int dano = rs.getInt("dano");
	            String effect = rs.getString("effect");
	            String nomeTipo = rs.getString("nome_tipo"); // Obtém o nome do tipo

                TYPES tipoEnum = TYPES.valueOf(nomeTipo.toUpperCase());

	            // Alterado para passar o nome do tipo ao construtor
                move ataque = new move(dano,tipoEnum,nome,id);
                //Gdx.app.log("Loaded move",ataque.baseDamage + " - " + ataque.name);
                ataques.add(ataque);
            }
	    }
	    return ataques;
	}

    //Retorna apenas ataques de um tipo em especifico
	public static List<move> filtrar(List<move> ataques, TYPES tipo) {
	    List<move> ataquesFiltrados = new ArrayList<>();
	    for (move ataque : ataques) {
	        if (ataque.type == tipo) { // Compara ignorando maiúsculas/minúsculas
	            ataquesFiltrados.add(ataque);
	        }
	    }
	    return ataquesFiltrados;
	}

    // Gera uma lista de tipos e retorna essa lista
	public static List<Tipo> loadTipos(Connection conn) throws SQLException {
		List<Tipo> tipos = new ArrayList<>();

		String query = "SELECT id, tipo FROM tipos;";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String tipo = rs.getString("tipo");

				Tipo t = new Tipo(id, tipo);
				tipos.add(t);
			}
		}
		return tipos;
	}
}
