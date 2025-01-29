package io.github.PokemonGame.Classes.Database;

public class Tipo {
	private int id;
	private String tipo;

	public Tipo(int id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Tipo{id=" + id + ", tipo='" + tipo + "'}";
	}
}
