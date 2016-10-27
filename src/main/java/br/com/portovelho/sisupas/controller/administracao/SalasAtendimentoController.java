package br.com.portovelho.sisupas.controller.administracao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.enums.StatusSalaAtendimento;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;
import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.SalasAtendimentoRepository;
import br.com.portovelho.sisupas.repository.filter.SalaAtendimentoFiltro;
import br.com.portovelho.sisupas.service.SalaAtendimentoService;

@Controller
@RequestMapping("/administracao/salaAtendimento")
public class SalasAtendimentoController {

	private static final String SALA_ATEND_CAD_VIEW = "/administracao/salaAtendimento/CadastroSalaAtendimento";
	private static final String SALA_ATEND_PESQUISA_VIEW = "/administracao/salaAtendimento/PesquisaSalaAtendimento";

	@Autowired
	private SalaAtendimentoService salaAtendimentoService;
	
	@Autowired
	private SalasAtendimentoRepository salasAtendimentoRepository;

	@ModelAttribute("todosTiposSalas")
	public List<TipoSalaAtendimento> todosTiposSalas() {
		return Arrays.asList(TipoSalaAtendimento.values());
	}

	@ModelAttribute("todosStatusSalaAtendimento")
	public List<StatusSalaAtendimento> todosStatusSalaAtendimento() {
		return Arrays.asList(StatusSalaAtendimento.values());
	}

	/*
	 * @RequestMapping public ModelAndView
	 * listaSalaAtendimentos(@ModelAttribute("filtro") SalaAtendimentoFiltro
	 * filtro) { ModelAndView mv = new ModelAndView(SALA_ATEND_PESQUISA_VIEW);
	 * mv.addObject("salaAtendimentos", salaAtendimentoService.filtrar(filtro));
	 * return mv; }
	 */

	@GetMapping
	public ModelAndView listaSalaAtendimentos(@ModelAttribute("filtro") SalaAtendimentoFiltro filtro) {
		ModelAndView mv = new ModelAndView(SALA_ATEND_PESQUISA_VIEW);
		mv.addObject("salaAtendimentos", salasAtendimentoRepository.filtrar(filtro));
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novaSalaAtendimento() {
		ModelAndView mv = new ModelAndView(SALA_ATEND_CAD_VIEW);
		mv.addObject(new SalaAtendimento());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvarSalaAtendimento(@Validated SalaAtendimento salaAtendimento, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return SALA_ATEND_CAD_VIEW;
		}
		try {
			salaAtendimentoService.salvar(salaAtendimento);
			attributes.addFlashAttribute("mensagem", "Sala de atendimento salva com sucesso.");
			return "redirect:/administracao/salaAtendimento/novo";
		} catch (ConstraintViolationException e) {
			errors.rejectValue("numero", null, "Já existe uma sala de atendimento com esse número!");
			errors.rejectValue("descricao", null, "Já existe uma sala de atendimento com essa descrição!");
			errors.rejectValue("tipoSalaAtendimento", null, "Já existe uma sala de atendimento com esse tipo de sala!");
			return SALA_ATEND_CAD_VIEW;
		}
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoSalaAtendimento(@PathVariable("id") SalaAtendimento salaAtendimento) {
		ModelAndView mv = new ModelAndView(SALA_ATEND_CAD_VIEW);
		mv.addObject(salaAtendimento);
		return mv;
	}

	/*
	 * @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	 * public @ResponseBody String mudaStatusSalaAtendimento(@PathVariable Long
	 * id) { return salaAtendimentoService.mudarStatus(id); }
	 */

}