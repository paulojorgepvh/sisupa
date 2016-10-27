package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.ProcedimentoInterno;

@Repository
public interface ProcedimentosInternosRepository extends JpaRepository<ProcedimentoInterno, Long>{

	List<ProcedimentoInterno> findByDescricaoContainingOrderByDescricaoAsc(String descricao);

	ProcedimentoInterno findByDescricaoOrCodigo(String descricao, String codigo);

}
