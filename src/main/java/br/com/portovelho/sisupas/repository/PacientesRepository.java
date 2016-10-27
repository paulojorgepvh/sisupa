package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.model.Paciente;

@Repository
public interface PacientesRepository extends JpaRepository<Paciente, Long>{

	List<AtendimentoIdentificado> findByNomeCompletoContainingOrderByNomeCompletoAsc(String nome);

}
