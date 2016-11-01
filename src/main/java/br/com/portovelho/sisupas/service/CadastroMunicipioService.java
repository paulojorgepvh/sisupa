package br.com.portovelho.sisupas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.repository.MunicipiosRepository;
import br.com.portovelho.sisupas.service.exception.NomeMunicipioJaCadastradoException;

@Service
public class CadastroMunicipioService {
	
	@Autowired
	private MunicipiosRepository municipioRepository;

	@Transactional
	public void salvar(Municipio municipio) {
		Optional<Municipio> municipioOptional = municipioRepository.findByNomeIgnoreCaseAndUf(municipio.getNome(), municipio.getUf());
		if(municipioOptional.isPresent()){
			throw new NomeMunicipioJaCadastradoException("Já existe um município com esse nome nessa UF !");
		}
		
		municipioRepository.save(municipio);
	}
}
