package br.com.portovelho.sisupas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.AtendimentoNaoIdentificado;
import br.com.portovelho.sisupas.repository.AtendimentosNaoIdentificadosRepository;
import br.com.portovelho.sisupas.repository.filter.AtendimentoNaoIdentificadoFiltro;

@Service
public class AtendimentoNaoIdentificadoService {

	@Autowired
	private AtendimentosNaoIdentificadosRepository atendimentoNaoIdentificadoRepository;

	public List<AtendimentoNaoIdentificado> filtrar(AtendimentoNaoIdentificadoFiltro filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return atendimentoNaoIdentificadoRepository.findBySenhaContainingOrderBySenhaAsc(descricao);
	}

	public void salvar(AtendimentoNaoIdentificado atendimentoNaoIdentificado) {
		atendimentoNaoIdentificadoRepository.save(atendimentoNaoIdentificado);
	}

	public String mudarStatus(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
