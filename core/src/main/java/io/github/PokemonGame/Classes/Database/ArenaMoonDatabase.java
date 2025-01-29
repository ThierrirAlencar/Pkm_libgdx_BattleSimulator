package io.github.PokemonGame.Classes.Database;


import io.github.PokemonGame.Actors.Pokemon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArenaMoonDatabase {

	public static void main(String[] args) {

		try (Connection conn = DatabaseConnection.connect();) {
			List<Pokemon> pokemons = DataLoader.loadPokemons(conn);
			List<Ataque> ataques = DataLoader.loadAtaques(conn);
			List<Tipo> tipos = DataLoader.loadTipos(conn);
			List<Ataque> Filtro= DataLoader.filtrar(ataques, "Fire");

			// Exemplo: imprimir os Pokémon carregados.

			for (Pokemon p : pokemons) {
				System.out.printf("ID: %d, Nome: %s, Vida: %d, Tipo: %s%n", p.getcIndex()-1, p.getName(), p.getLife(),
						p.getType());
			}
			for (Ataque at : ataques) {
				System.out.printf("ID: %d, Nome: %s, Dano: %d,Effect: %s, Tipo: %s%n", at.getId(), at.getNome(),
						at.getDano(), at.getEffect(), at.getTipo());
			}
			for (Tipo type : tipos) {
				System.out.printf("ID: %d, Nome: %s, ", type.getId(), type.getTipo());
			}
			for(Ataque at: Filtro) {
				System.out.printf("ID: %d, Nome: %s, Dano: %d,Effect: %s, Tipo: %s%n", at.getId(), at.getNome(),
						at.getDano(), at.getEffect(), at.getTipo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
