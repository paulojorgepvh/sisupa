package br.com.portovelho.sisupas.enums;

public enum StatusSalaAtendimento {
	ABERTA("ABERTA"),EM_MANUTENCAO("EM MANUTENÇÃO"),INATIVA("INATIVA"),OCUPADA("OCUPADA");

	private String descricao;

	private StatusSalaAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
