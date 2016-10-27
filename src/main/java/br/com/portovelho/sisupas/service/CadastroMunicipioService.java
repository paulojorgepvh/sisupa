package br.com.portovelho.sisupas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.repository.MunicipiosRepository;
import br.com.portovelho.sisupas.repository.filter.MunicipioFiltro;
import br.com.portovelho.sisupas.service.exception.NomeMunicipioJaCadastradoException;

@Service
public class CadastroMunicipioService {
	
	@Autowired
	private MunicipiosRepository municipioRepository;

	public List<Municipio> filtrar(MunicipioFiltro filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return municipioRepository.findByNomeContainingOrderByNomeAsc(nome.toUpperCase());
	}
	
	@Transactional
	public void salvar(Municipio municipio) {
		Optional<Municipio> municipioOptional = municipioRepository.findByNomeIgnoreCaseAndUf(municipio.getNome(), municipio.getUf());
		if(municipioOptional.isPresent()){
			throw new NomeMunicipioJaCadastradoException("Já existe um município com esse nome nessa UF !");
		}
		
		municipioRepository.save(municipio);
	}

	/*public MensagemDeErro salvar(Municipio municipio) {
		MensagemDeErro mensagem = new MensagemDeErro();
		Municipio municipioRecuperado = municipioRepository.findByNome(municipio.getNome());
		if (municipioRecuperado != null) {
			mensagem.setMensagem("Já existe um município com esse nome nessa UF !");
			mensagem.setErro(true);
		} else {
			municipioRepository.save(municipio);
			mensagem.setMensagem("Município salvo com sucesso!");
			mensagem.setErro(false);
		}
		return mensagem;
	}*/
}
