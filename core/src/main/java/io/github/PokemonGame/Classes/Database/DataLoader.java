package io.github.PokemonGame.Classes.Database;


import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Classes.Generators;
import io.github.PokemonGame.Types.TYPES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
	public static ArrayList<Pokemon> loadPokemons(Connection conn) throws SQLException {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String query = "SELECT p.id, p.nome, p.vida, t.tipo AS nome_tipo " +
            "FROM pokemon p " +
            "JOIN tipo_pokemon tp ON p.id = tp.idPokemon " +
            "JOIN tipos t ON tp.tipo1 = t.id;";
        // Aqui eu estou substituindo os valores de placeholders pelo nome das colunas do banco de dados""

	    try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nome = rs.getString("nome");
	            int vida = rs.getInt("vida");
	            String nomeTipo = rs.getString("nome_tipo"); // Obtendo o nome do tipo

                TYPES tipoEnum = TYPES.valueOf(nomeTipo.toUpperCase());

                // Alterado para passar o nome do tipo ao construtor
                Generators gen = new Generators();
	            Pokemon pokemon = new Pokemon(id,tipoEnum,nome,0,vida,gen.genMoveList(tipoEnum));
	            pokemons.add(pokemon);
	        }
	    }
	    return pokemons; //Lista que será retornada com todos os pokemons
	}


    //Carrega uma lista de todos os tipos
	public static List<Ataque> loadAtaques(Connection conn) throws SQLException {
	    List<Ataque> ataques = new ArrayList<>();
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

	            // Alterado para passar o nome do tipo ao construtor
	            Ataque ataque = new Ataque(id, nome, dano, effect, nomeTipo);
	            ataques.add(ataque);
	        }
	    }
	    return ataques;
	}

    //Retorna apenas ataques de um tipo em especifico
	public static List<Ataque> filtrar(List<Ataque> ataques, String tipo) {
	    List<Ataque> ataquesFiltrados = new ArrayList<>();
	    for (Ataque ataque : ataques) {
	        if (ataque.getTipo().equalsIgnoreCase(tipo)) { // Compara ignorando maiúsculas/minúsculas
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
