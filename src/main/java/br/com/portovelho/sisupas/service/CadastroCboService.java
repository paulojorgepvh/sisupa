/**
 * 
 */
package br.com.portovelho.sisupas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.CbosRepository;
import br.com.portovelho.sisupas.service.exception.NomeMunicipioJaCadastradoException;

/**
 * @author paulojorge
 *
 */
@Service
public class CadastroCboService {

	@Autowired
	private CbosRepository cbosRepository;

	@Transactional
	public void salvar(CBO cbo) {
		Optional<CBO> cboOptional = cbosRepository.findByDescricaoIgnoreCaseAndCodigo(cbo.getDescricao(),cbo.getCodigo());
		if(cboOptional.isPresent()){
			throw new NomeMunicipioJaCadastradoException("Já existe um CBO com essa descrição e/ou com esse código!");
		}
		
		cbosRepository.save(cbo);
	}
}
