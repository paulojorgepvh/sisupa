package br.com.portovelho.sisupas.enums;

public enum StatusSalaAtendimento {
	ABERTA("Aberta"),EM_MANUTENCAO("Em Manutenção"),INATIVA("Inativa"),OCUPADA("Ocupada");

	private String descricao;

	private StatusSalaAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
