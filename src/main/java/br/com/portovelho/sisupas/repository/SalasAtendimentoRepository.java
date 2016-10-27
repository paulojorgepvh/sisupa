package br.com.portovelho.sisupas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.helper.salaAtendimento.SalasAtendimentoRepositoryQueries;

@Repository
public interface SalasAtendimentoRepository extends JpaRepository<SalaAtendimento, Long>, SalasAtendimentoRepositoryQueries{
}