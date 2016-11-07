package br.com.portovelho.sisupas.repository.helper.procedimentoInterno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.filter.ProcedimentoInternoFiltro;

public interface ProcedimentosInternosRepositoryQueries {
	
	public Page<ProcedimentoInterno> filtrar(ProcedimentoInternoFiltro filtro, Pageable pageable);

}
