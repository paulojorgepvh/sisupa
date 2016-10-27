package br.com.portovelho.sisupas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.ProfissionaisSaudeRepository;


@Service
public class ProfissionalSaudeService {

	@Autowired
	private ProfissionaisSaudeRepository profissionaisSaudeRepository;

	/*
	 * @Autowired private CboRepository cboRepository;
	 */

	/*public List<ProfissionalSaude> buscarTodos() {

		return profissionalSaudeRepository.findAllByOrderByNomeCompletoAsc();

	}*/

	public void salvar(ProfissionalSaude profissionalSaude) {
		profissionaisSaudeRepository.save(profissionalSaude);
	}

	public String mudarStatus(Long id) {
		ProfissionalSaude profissionalSaude = profissionaisSaudeRepository.findOne(id);
		if (profissionalSaude.getAtivo()) {
			profissionalSaude.setAtivo(false);
		} else {
			profissionalSaude.setAtivo(true);
		}
		profissionaisSaudeRepository.save(profissionalSaude);
		return "Status alterado com sucesso.";
	}

	/*public List<ProfissionalSaude> filtrar(ProfissionalSaudeFiltro filtro) {
		String nomeCompleto = filtro.getNomeCompleto() == null ? "%" : filtro.getNomeCompleto();
		return profissionalSaudeRepository.findByNomeCompletoContainingOrderByNomeCompletoAsc(nomeCompleto);
	}*/
}
