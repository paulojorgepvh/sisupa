package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.UF;

@Repository
public interface UfsRepository extends JpaRepository<UF, Long>{

	public List<UF> findAllByOrderByNomeAsc();
}
