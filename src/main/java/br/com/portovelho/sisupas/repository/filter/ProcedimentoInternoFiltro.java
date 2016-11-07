package br.com.portovelho.sisupas.repository.filter;

import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;

public class ProcedimentoInternoFiltro {

	private String descricao;
	
	private TipoSalaAtendimento tipoSalaAtendimento;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoSalaAtendimento getTipoSalaAtendimento() {
		return tipoSalaAtendimento;
	}

	public void setTipoSalaAtendimento(TipoSalaAtendimento tipoSalaAtendimento) {
		this.tipoSalaAtendimento = tipoSalaAtendimento;
	}
}
