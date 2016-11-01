package br.com.portovelho.sisupas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.helper.cbo.CbosRepositoryQueries;

@Repository
public interface CbosRepository extends JpaRepository<CBO, Long>, CbosRepositoryQueries{

	public List<CBO> findAllByOrderByDescricaoAsc();

	public List<CBO> findByDescricaoContainingOrderByDescricaoAsc(String descricao);

	public Optional<CBO> findByDescricaoIgnoreCaseAndCodigo(String descricao, Long codigo);
}
