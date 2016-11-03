package br.com.portovelho.sisupas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.helper.motivoAtendimento.MotivosAtendimentosRepositoryQueries;

@Repository
public interface MotivosAtendimentosRepository extends JpaRepository<MotivoAtendimento, Long>, MotivosAtendimentosRepositoryQueries {
    
	public Optional<MotivoAtendimento> findByDescricaoIgnoreCase(String descricao);

}
