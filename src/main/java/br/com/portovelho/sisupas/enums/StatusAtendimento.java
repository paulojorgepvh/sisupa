package br.com.portovelho.sisupas.enums;

public enum StatusAtendimento {
	ABERTO("ABERTO"), FINALIZADO("FINALIZADO"), FECHADO("FECHADO"), EVASÃO("EVASÃO"), RECHAMADA("RECHAMADA"), NADA(
			"NADA"), FINALIZADO_PELO_SISTEMA("FINALIZADO PELO SISTEMA");

	private String descricao;

	private StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
