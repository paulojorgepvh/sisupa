package br.com.portovelho.sisupas.repository.helper.atendimentoNaoIdentificado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.AtendimentoNaoIdentificado;
import br.com.portovelho.sisupas.repository.filter.AtendimentoNaoIdentificadoFiltro;

public interface AtendimentosNaoIdentificadosRepositoryQueries {

	public Page<AtendimentoNaoIdentificado> filtrar(AtendimentoNaoIdentificadoFiltro filtro, Pageable pageable);
}
