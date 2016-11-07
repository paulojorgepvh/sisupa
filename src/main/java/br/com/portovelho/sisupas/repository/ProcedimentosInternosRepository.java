package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.helper.procedimentoInterno.ProcedimentosInternosRepositoryQueries;

@Repository
public interface ProcedimentosInternosRepository extends JpaRepository<ProcedimentoInterno, Long>, ProcedimentosInternosRepositoryQueries{

	List<ProcedimentoInterno> findByDescricaoContainingOrderByDescricaoAsc(String descricao);

	ProcedimentoInterno findByDescricaoOrCodigo(String descricao, String codigo);

}
