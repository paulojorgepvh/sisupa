package br.com.portovelho.sisupas.repository.helper.profissionalSaude;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.filter.ProfissionalSaudeFiltro;

public interface ProfissionaisSaudeRepositoryQueries {

	public Page<ProfissionalSaude> filtrar(ProfissionalSaudeFiltro filtro, Pageable pageable);

}
