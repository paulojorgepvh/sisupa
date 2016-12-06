package br.com.portovelho.sisupas.enums;

public enum TipoIdade {

	ANOS("ANOS"), MESES("MESES"), DIAS("DIAS");

	private String descricao;

	private TipoIdade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
