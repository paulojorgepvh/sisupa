package br.com.portovelho.sisupas.repository.helper.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.Paciente;
import br.com.portovelho.sisupas.repository.filter.PacienteFiltro;

public interface PacientesRepositoryQueries {

	public Page<Paciente> filtrar(PacienteFiltro filtro, Pageable pageable);
}
