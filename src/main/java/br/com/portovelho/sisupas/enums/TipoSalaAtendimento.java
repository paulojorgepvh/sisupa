package br.com.portovelho.sisupas.enums;

public enum TipoSalaAtendimento {
	ASSISTENCIA_SOCIAL("Assistência Social"),
	CONSULTORIO_MED("Consultório Médico"),
	CONSULTORIO_MED_PED("Consultório Médico Pediátrico"),
//	FARMACIA("Farmácia"),
//	GERAL("Geral"),
	ELETRO("Eletrocardiograma"),
	LABORATORIO("Laboratório"),
	MEDICACAO("Medicação"),
	OBSERVACAO_FEM("Observação Feminina"),
	OBSERVACAO_MAS("Observação Masculina"),
	OBSERVACAO_PED("Observação Pediátrica"),
	ODONTOLOGIA("Odontologia"),
	PROCEDIMENTO("Procedimento"),
	RADIOLOGIA("Radiologia"),
	SALA_VERMELHA("Sala Vermelha"),
	SUTURA("Sutura"),
	TRIAGEM("Triagem");
//	ULTRASONOGRAFIA("Ultrasonografia");
				
	private String descricao;


	private TipoSalaAtendimento(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
