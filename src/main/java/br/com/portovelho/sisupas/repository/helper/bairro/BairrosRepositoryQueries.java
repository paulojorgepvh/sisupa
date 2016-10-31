package br.com.portovelho.sisupas.repository.helper.bairro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.filter.BairroFiltro;

public interface BairrosRepositoryQueries {

	public Page<Bairro> filtrar(BairroFiltro filtro, Pageable pageable);
}
