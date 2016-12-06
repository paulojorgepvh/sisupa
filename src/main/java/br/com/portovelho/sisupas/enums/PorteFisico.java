package br.com.portovelho.sisupas.enums;

public enum PorteFisico {
	NORMAL("NORMAL"), GORDO("GORDO"), MAGRO("MAGRO");

	private String descricao;

	private PorteFisico(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
