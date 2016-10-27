package br.com.portovelho.sisupas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.Endereco;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, Long>{

}
