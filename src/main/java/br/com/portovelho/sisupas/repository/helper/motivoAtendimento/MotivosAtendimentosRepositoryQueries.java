package br.com.portovelho.sisupas.repository.helper.motivoAtendimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.filter.MotivoAtendimentoFiltro;

public interface MotivosAtendimentosRepositoryQueries {

	public Page<MotivoAtendimento> filtrar(MotivoAtendimentoFiltro filtro, Pageable pageable);
}
