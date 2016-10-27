package br.com.portovelho.sisupas.enums;

public enum DetalhesDoAtendimento {

	TRAUMA("Trauma"), SUSPEITA_DE_VIOLÊNCIA_E_OU_MAUS_TRATOS("Suspeita de violência e/ou maus tratos"), CASO_DE_POLÍCIA(
			"Caso de polícia"), ACIDENTE_DE_TRABALHO("Acidente de trabalho"), ACIDENTE_DE_TRÂNSITO(
					"Acidente de trânsito"), TRANSPORTADO_DE_AMBULÂNCIA("Transportado de ambulância");

	private String descricao;

	private DetalhesDoAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
