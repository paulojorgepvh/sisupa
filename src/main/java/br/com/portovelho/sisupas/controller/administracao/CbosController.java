package br.com.portovelho.sisupas.controller.administracao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.CbosRepository;
import br.com.portovelho.sisupas.repository.filter.CboFiltro;
import br.com.portovelho.sisupas.service.CboService;

@Controller
@RequestMapping("/administracao/cbos")
public class CbosController {

	private static final String CBO_CAD_VIEW = "/administracao/cbo/CadastroCbo";
	private static final String CBO_PESQUISA_VIEW = "/administracao/cbo/PesquisaCbo";

	@Autowired
	private CboService cboService;

	/*
	 * @Autowired private CadastroCboService cadastroCboService;
	 */

	@Autowired
	private CbosRepository cbosRepository;

	@GetMapping
	public ModelAndView listaCbos(@ModelAttribute("filtro") CboFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(CBO_PESQUISA_VIEW);

		PageWrapper<CBO> paginaWrapper = new PageWrapper<>(cbosRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoCbo(CBO cbo) {
		ModelAndView mv = new ModelAndView(CBO_CAD_VIEW);
		return mv;
	}

	/*@PostMapping("/novo")
	public ModelAndView salvarCbo(@Valid CBO cbo, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoCbo(cbo);
		}
		try {
			cadastroCboService.salvar(cbo);
		} catch (NomeMunicipioJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return novoCbo(cbo);
		}
		attributes.addFlashAttribute("mensagem", "CBO salvo com sucesso!");
		return new ModelAndView("redirect:/administracao/cbos/novo");
	}*/

	@PostMapping("/novo")
	public ModelAndView salvarCbo(@Valid CBO cbo, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoCbo(cbo);
		}
		try {
			cboService.salvar(cbo);
			attributes.addFlashAttribute("mensagem", "CBO salvo com sucesso!");
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_codigo_cbo")) {
				result.rejectValue("codigo", null, "Já existe um cbo com esse código!");
			}
			if (e.getConstraintName().equals("unique_descricao_cbo")) {
				result.rejectValue("descricao", null, "Já existe um cbo com essa descrição!");
			}
			return novoCbo(cbo);
		}

		return new ModelAndView("redirect:/administracao/cbos/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoCbo(@PathVariable("id") CBO cbo) {
		ModelAndView mv = new ModelAndView(CBO_CAD_VIEW);
		mv.addObject(cbo);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusCbo(@PathVariable Long id) {
		return cboService.mudarStatus(id);
	}

}
