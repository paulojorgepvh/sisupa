package br.com.portovelho.sisupas.enums;

public enum Raca {

	BRANCO("BRANCO"), NEGRO("NEGRO"), PARDO("PARDO"), AMARELA("AMARELO"), INDÍGINA("INDÍGINA"), DESCONHECIDA(
			"DESCONHECIDA");

	private String descricao;

	private Raca(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
