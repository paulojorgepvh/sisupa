package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.AtendimentoNaoIdentificado;
import br.com.portovelho.sisupas.repository.helper.atendimentoNaoIdentificado.AtendimentosNaoIdentificadosRepositoryQueries;

@Repository
public interface AtendimentosNaoIdentificadosRepository extends JpaRepository<AtendimentoNaoIdentificado, Long>,AtendimentosNaoIdentificadosRepositoryQueries {

	List<AtendimentoNaoIdentificado> findBySenhaContainingOrderBySenhaAsc(String descricao);
}