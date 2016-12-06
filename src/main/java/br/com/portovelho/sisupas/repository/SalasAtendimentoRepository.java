package br.com.portovelho.sisupas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.enums.StatusSalaAtendimento;
import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.helper.salaAtendimento.SalasAtendimentoRepositoryQueries;

@Repository
public interface SalasAtendimentoRepository extends JpaRepository<SalaAtendimento, Long>, SalasAtendimentoRepositoryQueries{
	
	public Optional<SalaAtendimento> findByDescricaoIgnoreCase(String descricao);

	public List<SalaAtendimento> findByStatusSalaAtendimentoOrStatusSalaAtendimentoOrderByDescricao(StatusSalaAtendimento aberta,
			StatusSalaAtendimento ocupada);
	
	//public List<SalaAtendimento> findByStatusSalaAtendimentoOrStatusSalaAtendimento();

}
