package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.CBO;

@Repository
public interface CbosRepository extends JpaRepository<CBO, Long>{

	public List<CBO> findAllByOrderByDescricaoAsc();

	public List<CBO> findByDescricaoContainingOrderByDescricaoAsc(String descricao);
}
