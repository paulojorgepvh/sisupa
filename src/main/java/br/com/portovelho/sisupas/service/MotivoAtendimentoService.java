package br.com.portovelho.sisupas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.MotivosAtendimentosRepository;


@Service
public class MotivoAtendimentoService {

	@Autowired
	private MotivosAtendimentosRepository motivosAtendimentoRepository;

	public String mudarStatus(Long id) {
		MotivoAtendimento motivoAtendimento = motivosAtendimentoRepository.findOne(id);
		if (motivoAtendimento.getStatus()) {
			motivoAtendimento.setStatus(false);
		} else {
			motivoAtendimento.setStatus(true);
		}
		motivosAtendimentoRepository.save(motivoAtendimento);
		return "Status alterado com sucesso.";
	}

}
