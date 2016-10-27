package br.com.portovelho.sisupas.enums;

public enum Raca {

	BRANCA("Branco"), PRETA("Negro"), PARDA("Pardo"), AMARELA("Amarela"), INDÍGINA("Indígina"), DESCONHECIDA(
			"Desconhecida");

	private String descricao;

	private Raca(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
