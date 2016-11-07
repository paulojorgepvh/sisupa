package br.com.portovelho.sisupas.repository.helper.salaAtendimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.filter.SalaAtendimentoFiltro;

public interface SalasAtendimentoRepositoryQueries {

	public Page<SalaAtendimento> filtrar(SalaAtendimentoFiltro filtro, Pageable pageable);

}
