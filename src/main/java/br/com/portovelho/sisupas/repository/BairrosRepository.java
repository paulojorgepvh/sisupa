package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.helper.bairro.BairrosRepositoryQueries;

@Repository
public interface BairrosRepository extends JpaRepository<Bairro, Long>, BairrosRepositoryQueries {
	public List<Bairro> findAllByOrderByNomeAsc();

	public List<Bairro> findByNomeContainingOrderByNomeAsc(String nome);

}
