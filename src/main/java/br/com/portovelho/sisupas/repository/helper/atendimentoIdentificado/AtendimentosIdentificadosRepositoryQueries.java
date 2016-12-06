package br.com.portovelho.sisupas.repository.helper.atendimentoIdentificado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.filter.AtendimentoIdentificadoFiltro;

public interface AtendimentosIdentificadosRepositoryQueries {

	public Page<AtendimentoIdentificado> filtrar(AtendimentoIdentificadoFiltro filtro, Pageable pageable);
}
