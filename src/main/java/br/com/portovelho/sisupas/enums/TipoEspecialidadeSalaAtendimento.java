package br.com.portovelho.sisupas.enums;

public enum TipoEspecialidadeSalaAtendimento {
	ASSISTENCIA_SOCIAL("Assistência Social"),
	FEMININA("Feminina"), 
	MASCULINA("Masculina"),
	NORMAL("Normal"), 
	ODONTOLOGIA("Odontologia"),
	PEDIATRIA("Pediatria"),
	SALA_VERMELHA("Sala Vermelha"),
	SUTURA("Sutura");

	private String descricao;

	private TipoEspecialidadeSalaAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
