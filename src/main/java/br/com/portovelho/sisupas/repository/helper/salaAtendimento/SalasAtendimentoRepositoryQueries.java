package br.com.portovelho.sisupas.repository.helper.salaAtendimento;

import java.util.List;

import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.filter.SalaAtendimentoFiltro;

public interface SalasAtendimentoRepositoryQueries {

	public List<SalaAtendimento> filtrar(SalaAtendimentoFiltro filtro);

}
