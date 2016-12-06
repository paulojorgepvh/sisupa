package br.com.portovelho.sisupas.enums;

public enum Estrutura {
	NORMAL("NORMAL"), ALTO("ALTO"), BAIXO("BAIXO");

	private final String descricao;

	private Estrutura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
