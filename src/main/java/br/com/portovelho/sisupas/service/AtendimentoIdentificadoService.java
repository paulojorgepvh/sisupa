package br.com.portovelho.sisupas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.PacientesRepository;
import br.com.portovelho.sisupas.repository.filter.AtendimentoIdentificadoFiltro;


@Service
public class AtendimentoIdentificadoService {

	/*@Autowired
	private AtendimentoIdentificadoRepository atendimentoIdentificadoRepository;*/
	
	private PacientesRepository pacientesRepository;

	public List<AtendimentoIdentificado> filtrar(AtendimentoIdentificadoFiltro filtro) {
		String nome = filtro.getNomeCompleto() == null ? "%" : filtro.getNomeCompleto();
		return pacientesRepository.findByNomeCompletoContainingOrderByNomeCompletoAsc(nome);
	}
}
