package io.github.PokemonGame.Classes.Database;

public class Ataque {
	private int id;
	private String nome;
	private int dano;
	private String effect;
	private String tipo;

	public Ataque(int id, String nome, int dano, String effect, String tipo) {
		this.setId(id);
		this.nome = nome;
		this.dano = dano;
		this.effect = effect;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
