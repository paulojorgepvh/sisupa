package br.com.portovelho.sisupas.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.SalasAtendimentoRepository;

@Service
public class CadastroSalaAtendimentoService {
	
	@Autowired
	private SalasAtendimentoRepository salasAtendimentoRepository;

	public void salvar(SalaAtendimento salaAtendimento) {
		try {
			salasAtendimentoRepository.save(salaAtendimento);
		} catch (DataIntegrityViolationException e) {
			throw (ConstraintViolationException) e.getCause();
		}		
	}

}
