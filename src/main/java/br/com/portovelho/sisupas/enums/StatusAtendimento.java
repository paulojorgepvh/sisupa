package br.com.portovelho.sisupas.enums;

public enum StatusAtendimento {
	ABERTO("Aberto"), FINALIZADO("Finalizado"), FECHADO("Fechado"), EVASÃO("Evasão"), RECHAMADA("Rechamada"), NADA(
			"Nada"), FINALIZADO_PELO_SISTEMA("Finalizado pelo sistema");

	private String descricao;

	private StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
