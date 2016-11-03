package br.com.portovelho.sisupas.controller.administracao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.repository.MotivosAtendimentosRepository;
import br.com.portovelho.sisupas.repository.filter.MotivoAtendimentoFiltro;
import br.com.portovelho.sisupas.service.CadastroMotivoAtendimentoService;
import br.com.portovelho.sisupas.service.MotivoAtendimentoService;
import br.com.portovelho.sisupas.service.exception.DescricoMotivoAtendimentoJaCadastradoException;

@Controller
@RequestMapping("/administracao/motivosAtendimentos")
public class MotivosAtendimentoController {

	private static final String MOTIVO_ATEND_CAD_VIEW = "/administracao/motivoAtendimento/CadastroMotivoAtendimento";
	private static final String MOTIVO_ATEND_PESQUISA_VIEW = "/administracao/motivoAtendimento/PesquisaMotivoAtendimento";

	@Autowired
	private MotivoAtendimentoService motivoAtendimentoService;
	
	@Autowired
	private MotivosAtendimentosRepository motivosAtendimentosRepository;
	
	@Autowired
	private CadastroMotivoAtendimentoService cadastroMotivoAtendimentoService;

	@GetMapping
	public ModelAndView listaMotivosAtendimento(@ModelAttribute("filtro") MotivoAtendimentoFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(MOTIVO_ATEND_PESQUISA_VIEW);

		PageWrapper<MotivoAtendimento> paginaWrapper = new PageWrapper<>(motivosAtendimentosRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoMotivoAtendimento(MotivoAtendimento motivoAtendimento) {
		ModelAndView mv = new ModelAndView(MOTIVO_ATEND_CAD_VIEW);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvarMotivoAtendimento(@Valid MotivoAtendimento motivoAtendimento, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoMotivoAtendimento(motivoAtendimento);
		}
		try {
			cadastroMotivoAtendimentoService.salvar(motivoAtendimento);
		} catch (DescricoMotivoAtendimentoJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novoMotivoAtendimento(motivoAtendimento);
		}
		attributes.addFlashAttribute("mensagem", "Motivo de atendimento salvo com sucesso!");
		return new ModelAndView("redirect:/administracao/motivosAtendimentos/novo");
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
