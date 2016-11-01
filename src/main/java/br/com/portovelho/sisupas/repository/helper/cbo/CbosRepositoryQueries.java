package br.com.portovelho.sisupas.repository.helper.cbo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.filter.CboFiltro;

public interface CbosRepositoryQueries {
	
	public Page<CBO> filtrar(CboFiltro filtro, Pageable pageable);
}
