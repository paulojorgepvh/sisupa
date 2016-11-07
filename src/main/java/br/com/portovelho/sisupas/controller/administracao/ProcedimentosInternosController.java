package br.com.portovelho.sisupas.controller.administracao;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;
import br.com.portovelho.sisupas.model.ProcedimentoInterno;
import br.com.portovelho.sisupas.repository.ProcedimentosInternosRepository;
import br.com.portovelho.sisupas.repository.filter.ProcedimentoInternoFiltro;
import br.com.portovelho.sisupas.service.ProcedimentoInternoService;


@Controller
@RequestMapping("/administracao/procedimentosInternos")
public class ProcedimentosInternosController {

	private static final String PROCED_INTERNO_CAD_VIEW = "/administracao/procedimentoInterno/CadastroProcedimentoInterno";
	private static final String PROCED_INTERNO_PESQUISA_VIEW = "/administracao/procedimentoInterno/PesquisaProcedimentoInterno";

	@Autowired
	private ProcedimentoInternoService procedimentoInternoService;
	
	@Autowired
	private ProcedimentosInternosRepository procedimentosInternosRepository;

	@GetMapping
	public ModelAndView listaProcedimentosInterno(@ModelAttribute("filtro") ProcedimentoInternoFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_PESQUISA_VIEW);
		mv.addObject("todosTiposSalas",TipoSalaAtendimento.values());
		
		PageWrapper<ProcedimentoInterno> paginaWrapper = new PageWrapper<>(procedimentosInternosRepository.filtrar(filtro, pageable),httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoProcedimentoInterno(ProcedimentoInterno procedimentoInterno) {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_CAD_VIEW);
		mv.addObject("todosTiposSalas",TipoSalaAtendimento.values());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvarProcedimentoInterno(@Validated ProcedimentoInterno procedimentoInterno, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return novoProcedimentoInterno(procedimentoInterno);
		}
		try {
			procedimentoInternoService.salvar(procedimentoInterno);
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_codigo")) {
				errors.rejectValue("codigo", null, "Já existe um procedimento interno com esse código!");
			}
			if (e.getConstraintName().equals("unique_descricao")) {
				errors.rejectValue("descricao", null, "Já existe um procedimento interno com essa descrição!");
			}
			return novoProcedimentoInterno(procedimentoInterno);
		}
		attributes.addFlashAttribute("mensagem", "Procedimento interno salvo com sucesso.");
		return new ModelAndView("redirect:/administracao/procedimentosInternos/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoProcedimentoInterno(@PathVariable("id") ProcedimentoInterno procedimentoInterno) {
		ModelAndView mv = new ModelAndView(PROCED_INTERNO_CAD_VIEW);
		mv.addObject("todosTiposSalas",TipoSalaAtendimento.values());
		mv.addObject(procedimentoInterno);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusProcedimentoInterno(@PathVariable Long id) {
		return procedimentoInternoService.mudarStatus(id);
	}
}
