package io.github.PokemonGame.Classes.Database;


import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Types.TYPES;
import io.github.PokemonGame.Classes.move;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArenaMoonDatabase {

	public static void main(String[] args) {

		try (Connection conn = DatabaseConnection.connect();) {
			List<Pokemon> pokemons = DataLoader.loadPokemons(conn);
			List<move> ataques = DataLoader.loadAtaques(conn);
			List<Tipo> tipos = DataLoader.loadTipos(conn);
			List<move> Filtro= DataLoader.filtrar(ataques, TYPES.FIRE);

			// Exemplo: imprimir os Pokémon carregados.

			for (Pokemon p : pokemons) {
				System.out.printf("ID: %d, Nome: %s, Vida: %d, Tipo: %s%n", p.getcIndex()-1, p.getName(), p.getLife(),
						p.getType());
			}
			for (move at : ataques) {
				System.out.printf("ID: %d, Nome: %s, Dano: %d,Effect: %s, Tipo: %s%n", at.id, at.name,
						at.baseDamage, at.effect, at.type);
			}
			for (Tipo type : tipos) {
				System.out.printf("ID: %d, Nome: %s, ", type.getId(), type.getTipo());
			}
			for(move at: Filtro) {
				System.out.printf("ID: %d, Nome: %s, Dano: %d,Effect: %s, Tipo: %s%n", at.id, at.name,
                    at.baseDamage, at.effect, at.type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
