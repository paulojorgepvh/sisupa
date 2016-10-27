package br.com.portovelho.sisupas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.helper.profissionalSaude.ProfissionaisSaudeRepositoryQueries;

@Repository
public interface ProfissionaisSaudeRepository extends JpaRepository<ProfissionalSaude, Long>,ProfissionaisSaudeRepositoryQueries{

}
