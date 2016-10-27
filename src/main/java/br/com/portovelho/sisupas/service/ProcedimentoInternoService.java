package br.com.portovelho.sisupas.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.ProcedimentosInternosRepository;
import br.com.portovelho.sisupas.repository.filter.ProcedimentoInternoFiltro;


@Service
public class ProcedimentoInternoService {

	@Autowired
	private ProcedimentosInternosRepository procedimentosInternosRepository;

	public List<ProcedimentoInterno> filtrar(ProcedimentoInternoFiltro filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return procedimentosInternosRepository.findByDescricaoContainingOrderByDescricaoAsc(descricao.toUpperCase());
	}

	public String mudarStatus(Long id) {
		ProcedimentoInterno procedimentoInterno = procedimentosInternosRepository.findOne(id);
		if (procedimentoInterno.getStatus()) {
			procedimentoInterno.setStatus(false);
		} else {
			procedimentoInterno.setStatus(true);
		}
		procedimentosInternosRepository.save(procedimentoInterno);
		return "Status alterado com sucesso.";
	}

	public void salvar(ProcedimentoInterno procedimentoInterno) {
		try {
			procedimentosInternosRepository.save(procedimentoInterno);
		} catch (DataIntegrityViolationException e) {
			throw (ConstraintViolationException) e.getCause();
		}
	}

}
