package br.com.portovelho.sisupas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portovelho.sisupas.model.MotivoAtendimento;

@Repository
public interface MotivosAtendimentosRepository extends JpaRepository<MotivoAtendimento, Long> {
    
    public List<MotivoAtendimento> findByDescricaoContainingOrderByDescricaoAsc(String descricao);

    public MotivoAtendimento findByDescricao(String descricao);

    public List<MotivoAtendimento> findAllByOrderByDescricao();
}
