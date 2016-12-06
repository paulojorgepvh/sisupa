package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.helper.atendimentoIdentificado.AtendimentosIdentificadosRepositoryQueries;

@Repository
public interface AtendimentosIdentificadosRepository extends JpaRepository<AtendimentoIdentificado, Long>,AtendimentosIdentificadosRepositoryQueries{

	List<AtendimentoIdentificado> findBySenhaOrderByPacienteNomeCompletoAsc(String senha);


}
