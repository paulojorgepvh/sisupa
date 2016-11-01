package br.com.portovelho.sisupas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.BairrosRepository;
import br.com.portovelho.sisupas.service.exception.NomeBairroJaCadastradoException;

@Service
public class CadastroBairroService {
	
	@Autowired
	private BairrosRepository bairrosRepository;

	@Transactional
	public void salvar(Bairro bairro) {
		Optional<Bairro> bairroOptional = bairrosRepository.findByNomeIgnoreCaseAndMunicipio(bairro.getNome(), bairro.getMunicipio());
		if(bairroOptional.isPresent()){
			throw new NomeBairroJaCadastradoException("Já existe um bairro com esse nome para esse município!");
		}
		
		bairrosRepository.save(bairro);
	}

}
