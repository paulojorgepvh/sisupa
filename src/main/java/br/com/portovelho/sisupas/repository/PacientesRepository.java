package br.com.portovelho.sisupas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.model.Paciente;
import br.com.portovelho.sisupas.repository.helper.paciente.PacientesRepositoryQueries;

@Repository
public interface PacientesRepository extends JpaRepository<Paciente, Long>, PacientesRepositoryQueries{

	List<AtendimentoIdentificado> findByNomeCompletoContainingOrderByNomeCompletoAsc(String nome);

	Optional<Paciente> findByCpf(String cpf);

}