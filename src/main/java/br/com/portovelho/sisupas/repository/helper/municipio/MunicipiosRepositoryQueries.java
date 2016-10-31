package br.com.portovelho.sisupas.repository.helper.municipio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.repository.filter.MunicipioFiltro;

public interface MunicipiosRepositoryQueries {

	public Page<Municipio> filtrar(MunicipioFiltro filtro, Pageable pageable);
}
