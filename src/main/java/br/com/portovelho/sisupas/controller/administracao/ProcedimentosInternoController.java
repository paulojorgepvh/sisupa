package br.com.portovelho.sisupas.controller.administracao;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;
import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.filter.ProcedimentoInternoFiltro;
import br.com.portovelho.sisupas.service.ProcedimentoInternoService;


@Controller
@RequestMapping("/administracao/procedimentosInternos")
public class ProcedimentosInternoController {

	private static final String PROCED_INTERNO_CAD_VIEW = "/administracao/procedimentoInterno/CadastroProcedimentoInterno";
	private static final String PROCED_INTERNO_PESQUISA_VIEW = "/administracao/procedimentoInterno/PesquisaProcedimentoInterno";

	@Autowired
	private ProcedimentoInternoService procedimentoInternoService;

	/*@ModelAttribute("todosTiposSalas")
	public List<TipoSalaAtendimento> todosTiposSalas() {
		return Arrays.asList(TipoSalaAtendimento.values());
	}*/

	@RequestMapping
	public ModelAndView listaProcedimentosInterno(@ModelAttribute("filtro") ProcedimentoInternoFiltro filtro) {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_PESQUISA_VIEW);
		mv.addObject("procedimentos", procedimentoInternoService.filtrar(filtro));
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoProcedimentoInterno() {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_CAD_VIEW);
		mv.addObject(new ProcedimentoInterno());
		mv.addObject("todosTiposSalas",TipoSalaAtendimento.values());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvarProcedimentoInterno(@Validated ProcedimentoInterno procedimentoInterno, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return PROCED_INTERNO_CAD_VIEW;
		}
		try {
			procedimentoInternoService.salvar(procedimentoInterno);
			attributes.addFlashAttribute("mensagem", "Procedimento interno salvo com sucesso.");
			return "redirect:/administracao/procedimentoInterno/novo";
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_codigo")) {
				errors.rejectValue("codigo", null, "Já existe um procedimento interno com esse código!");
			}
			if (e.getConstraintName().equals("unique_descricao")) {
				errors.rejectValue("descricao", null, "Já existe um procedimento interno com essa descrição!");
			}
			return PROCED_INTERNO_CAD_VIEW;
		}
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoProcedimentoInterno(@PathVariable("id") ProcedimentoInterno procedimentoInterno) {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_CAD_VIEW);
		mv.addObject(procedimentoInterno);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusProcedimentoInterno(@PathVariable Long id) {
		return procedimentoInternoService.mudarStatus(id);
	}
}
