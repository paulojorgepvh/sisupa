package br.com.portovelho.sisupas.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.CbosRepository;
import br.com.portovelho.sisupas.repository.filter.CboFiltro;


@Service
public class CboService {
	
	@Autowired
	private CbosRepository cboRepository;

	public List<CBO> filtrar(CboFiltro filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return cboRepository.findByDescricaoContainingOrderByDescricaoAsc(descricao);
	}

	public void salvar(CBO cbo) {
		try {
			cboRepository.save(cbo);
		} catch (DataIntegrityViolationException e) {
			throw (ConstraintViolationException) e.getCause();
		}
	}

	public String mudarStatus(Long id) {
		CBO cbo = cboRepository.findOne(id);
		if (cbo.getStatus()) {
			cbo.setStatus(false);
		} else {
			cbo.setStatus(true);
		}
		cboRepository.save(cbo);
		return "Status alterado com sucesso.";
	}

}
