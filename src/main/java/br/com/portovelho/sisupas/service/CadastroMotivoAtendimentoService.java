package br.com.portovelho.sisupas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.MotivosAtendimentosRepository;
import br.com.portovelho.sisupas.service.exception.DescricoMotivoAtendimentoJaCadastradoException;

@Service
public class CadastroMotivoAtendimentoService {
	
	@Autowired
	private MotivosAtendimentosRepository motivosAtendimentosRepository;

	@Transactional
	public void salvar(MotivoAtendimento motivoAtendimento) {
		Optional<MotivoAtendimento> motivoAtendimentoOptional = motivosAtendimentosRepository.findByDescricaoIgnoreCase(motivoAtendimento.getDescricao());
		if(motivoAtendimentoOptional.isPresent()){
			throw new DescricoMotivoAtendimentoJaCadastradoException("Já existe um motivo de atendimento com essa descrição!");
		}
		
		motivosAtendimentosRepository.save(motivoAtendimento);
	}

}
