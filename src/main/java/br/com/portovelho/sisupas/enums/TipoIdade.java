package br.com.portovelho.sisupas.enums;

public enum TipoIdade {

	ANOS("Anos"), MESES("Meses"), DIAS("Dias");

	private String descricao;

	private TipoIdade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
