package br.com.portovelho.sisupas.enums;

public enum TipoSalaAtendimento {
	ASSISTENCIA_SOCIAL("ASSISTÊNCIA SOCIAL"),
	CONSULTORIO_MED("CONSULTÓRIO MÉDICO"),
	CONSULTORIO_MED_PED("CONSULTÓRIO MÉDICO PEDIÁTRICO"),
//	FARMACIA("Farmácia"),
//	GERAL("Geral"),
	ELETRO("ELETROCARDIOGRAMA"),
	LABORATORIO("LABORATÓRIO"),
	MEDICACAO("MEDICAÇÃO"),
	OBSERVACAO_FEM("OBSERVAÇÃO FEMINIA"),
	OBSERVACAO_MAS("OBSERVAÇÃO MASCULINA"),
	OBSERVACAO_PED("OBSERVAÇÃO PEDIÁTRICA"),
	ODONTOLOGIA("ODONTOLOGIA"),
	PROCEDIMENTOS("PROCEDIMENTOS"),
	RADIOLOGIA("RADIOLOGIA"),
	SALA_VERMELHA("SALA VERMELHA"),
	SUTURA("SUTURA"),
	TRIAGEM("TRIAGEM");
//	ULTRASONOGRAFIA("Ultrasonografia");
				
	private String descricao;


	private TipoSalaAtendimento(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
