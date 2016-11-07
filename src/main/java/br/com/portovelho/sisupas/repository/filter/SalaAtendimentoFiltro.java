package br.com.portovelho.sisupas.repository.filter;

import br.com.portovelho.sisupas.enums.StatusSalaAtendimento;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;

public class SalaAtendimentoFiltro {
	private String descricao;
	private TipoSalaAtendimento tipoSalaAtendimento;
	private StatusSalaAtendimento statusSalaAtendimento;

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

	public StatusSalaAtendimento getStatusSalaAtendimento() {
		return statusSalaAtendimento;
	}

	public void setStatusSalaAtendimento(StatusSalaAtendimento statusSalaAtendimento) {
		this.statusSalaAtendimento = statusSalaAtendimento;
	}
	
	
}
