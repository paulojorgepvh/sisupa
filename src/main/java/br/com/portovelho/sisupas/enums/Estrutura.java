package br.com.portovelho.sisupas.enums;

public enum Estrutura {
	NORMAL("Normal"), ALTO("Alto"), BAIXO("Baixo");

	private String descricao;

	private Estrutura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
