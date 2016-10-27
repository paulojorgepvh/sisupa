package br.com.portovelho.sisupas.controller.administracao;

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

import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.filter.MotivoAtendimentoFiltro;
import br.com.portovelho.sisupas.service.MotivoAtendimentoService;
import br.com.portovelho.sisupas.util.MensagemDeErro;

@Controller
@RequestMapping("/administracao/motivoAtendimento")
public class MotivosAtendimentoController {

	private static final String MOTIVO_ATEND_CAD_VIEW = "/administracao/motivoAtendimento/CadastroMotivoAtendimento";
	private static final String MOTIVO_ATEND_PESQUISA_VIEW = "/administracao/motivoAtendimento/PesquisaMotivoAtendimento";

	@Autowired
	private MotivoAtendimentoService motivoAtendimentoService;

	@RequestMapping
	public ModelAndView listaMotivosAtendimento(@ModelAttribute("filtro") MotivoAtendimentoFiltro filtro) {
		ModelAndView mv = new ModelAndView(MOTIVO_ATEND_PESQUISA_VIEW);
		mv.addObject("motivos", motivoAtendimentoService.filtrar(filtro));
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoMotivoAtendimento() {
		ModelAndView mv = new ModelAndView(MOTIVO_ATEND_CAD_VIEW);
		mv.addObject(new MotivoAtendimento());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvarMotivoAtendimento(@Validated MotivoAtendimento motivoAtendimento, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return MOTIVO_ATEND_CAD_VIEW;
		}
		MensagemDeErro mensagemDeErro = motivoAtendimentoService.salvar(motivoAtendimento);
		attributes.addFlashAttribute("mensagem", mensagemDeErro);
		return "redirect:/administracao/motivoAtendimento/novo";
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoMotivoAtendimento(@PathVariable("id") MotivoAtendimento motivoAtendimento) {
		ModelAndView mv = new ModelAndView(MOTIVO_ATEND_CAD_VIEW);
		mv.addObject(motivoAtendimento);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusMotivo(@PathVariable Long id) {
		return motivoAtendimentoService.mudarStatus(id);
	}

}
