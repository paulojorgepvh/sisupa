package br.com.portovelho.sisupas.enums;

public enum PerfilDeAcesso {
	ATD("ATENDENTE"),ADM("ADMINISTRADOR"), EF("ENFERMEIRO"),MD("MÉDICO");
	
	private String descricao;

	private PerfilDeAcesso(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
