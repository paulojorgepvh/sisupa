package br.com.portovelho.sisupas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.AtendimentosIdentificadosRepository;
import br.com.portovelho.sisupas.repository.filter.AtendimentoIdentificadoFiltro;


@Service
public class AtendimentoIdentificadoService {

	@Autowired
	private AtendimentosIdentificadosRepository atendimentosIdentificadosRepository;
	
//	@Autowired
//	private PacientesRepository pacientesRepository;

	public List<AtendimentoIdentificado> filtrar(AtendimentoIdentificadoFiltro filtro) {
		String senha = filtro.getSenha() == null ? "%" : filtro.getSenha();
		return atendimentosIdentificadosRepository.findBySenhaOrderByPacienteNomeCompletoAsc(senha);
	}
}
