package br.com.portovelho.sisupas.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.BairrosRepository;
import br.com.portovelho.sisupas.repository.filter.BairroFiltro;

@Service
public class BairroService {
	@Autowired
	private BairrosRepository bairroRepository;

	public List<Bairro> filtrar(BairroFiltro filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return bairroRepository.findByNomeContainingOrderByNomeAsc(nome.toUpperCase());
	}

	public void salvar(Bairro bairro) {
		try {
			bairroRepository.save(bairro);
		} catch (DataIntegrityViolationException e) {
			throw (ConstraintViolationException) e.getCause();
		}
	}

	public String mudarStatus(Long id) {
		Bairro bairro = bairroRepository.findOne(id);
		if (bairro.getStatus()) {
			bairro.setStatus(false);
		} else {
			bairro.setStatus(true);
		}
		bairroRepository.save(bairro);
		return "Status alterado com sucesso.";
	}

}
