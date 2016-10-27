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

import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.repository.filter.CboFiltro;
import br.com.portovelho.sisupas.service.CboService;

@Controller
@RequestMapping("/administracao/cbo")
public class CbosController {

    private static final String CBO_CAD_VIEW = "/administracao/cbo/CadastroCbo";
    private static final String CBO_PESQUISA_VIEW = "/administracao/cbo/PesquisaCbo";

    @Autowired
    private CboService cboService;

    @RequestMapping
    public ModelAndView listaCbos(@ModelAttribute("filtro") CboFiltro filtro) {
	ModelAndView mv = new ModelAndView(CBO_PESQUISA_VIEW);
	mv.addObject("cbos", cboService.filtrar(filtro));
	return mv;
    }

    @RequestMapping("/novo")
    public ModelAndView novoCbo() {
	ModelAndView mv = new ModelAndView(CBO_CAD_VIEW);
	mv.addObject(new CBO());
	return mv;
    }
    
    @RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvarCbo(@Validated CBO cbo, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CBO_CAD_VIEW;
		}
		try {
			cboService.salvar(cbo);
			attributes.addFlashAttribute("mensagem", "CBO salvo com sucesso.");
			return "redirect:/administracao/cbo/novo";
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_codigo_cbo")) {
				errors.rejectValue("codigo", null, "Já existe um cbo com esse código!");
			}
			if (e.getConstraintName().equals("unique_descricao_cbo")) {
				errors.rejectValue("descricao", null, "Já existe um cbo com essa descrição!");
			}
			return CBO_CAD_VIEW;
		}
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
