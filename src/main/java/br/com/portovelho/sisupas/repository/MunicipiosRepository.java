package br.com.portovelho.sisupas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.model.UF;

public interface MunicipiosRepository extends JpaRepository<Municipio, Long>{

	public List<Municipio> findAllByOrderByNome();

	public List<Municipio> findByNomeContainingOrderByNomeAsc(String upperCase);

	public Municipio findByNomeIgnoreCase(String nome);
	
	public Optional<Municipio> findByNomeIgnoreCaseAndUf(String nome, UF uf);
}
