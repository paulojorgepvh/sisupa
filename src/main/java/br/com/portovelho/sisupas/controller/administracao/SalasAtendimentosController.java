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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.enums.StatusSalaAtendimento;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;
import br.com.portovelho.sisupas.model.SalaAtendimento;
import br.com.portovelho.sisupas.repository.SalasAtendimentoRepository;
import br.com.portovelho.sisupas.repository.filter.SalaAtendimentoFiltro;
import br.com.portovelho.sisupas.service.CadastroSalaAtendimentoService;

@Controller
@RequestMapping("/administracao/salasAtendimentos")
public class SalasAtendimentosController {

	private static final String SALA_ATEND_CAD_VIEW = "/administracao/salaAtendimento/CadastroSalaAtendimento";
	private static final String SALA_ATEND_PESQUISA_VIEW = "/administracao/salaAtendimento/PesquisaSalaAtendimento";

	@Autowired
	private SalasAtendimentoRepository salasAtendimentoRepository;
	
	@Autowired
	private CadastroSalaAtendimentoService cadastroSalaAtendimentoService;
	
	@GetMapping
	public ModelAndView listaSalaAtendimentos(@ModelAttribute("filtro") SalaAtendimentoFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(SALA_ATEND_PESQUISA_VIEW);
		mv.addObject("todosTiposSalas", TipoSalaAtendimento.values());
		mv.addObject("todosStatusSalaAtendimento", StatusSalaAtendimento.values());

		PageWrapper<SalaAtendimento> paginaWrapper = new PageWrapper<>(salasAtendimentoRepository.filtrar(filtro, pageable),httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}


	@RequestMapping("/nova")
	public ModelAndView novaSalaAtendimento(SalaAtendimento salaAtendimento) {
		ModelAndView mv = new ModelAndView(SALA_ATEND_CAD_VIEW);
		mv.addObject("todosTiposSalas", TipoSalaAtendimento.values());
		mv.addObject("todosStatusSalaAtendimento", StatusSalaAtendimento.values());
		return mv;
	}

	@PostMapping("/nova")
	public ModelAndView salvarSalaAtendimento(@Valid SalaAtendimento salaAtendimento, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novaSalaAtendimento(salaAtendimento);
		}
		try {
			cadastroSalaAtendimentoService.salvar(salaAtendimento);
		} catch (ConstraintViolationException e) {
			result.rejectValue("numero", null, "Já existe uma sala de atendimento com esse número!");
			result.rejectValue("descricao", null, "Já existe uma sala de atendimento com essa descrição!");
			result.rejectValue("tipoSalaAtendimento", null, "Já existe uma sala de atendimento com esse tipo de sala!");
			return novaSalaAtendimento(salaAtendimento);
		}
		attributes.addFlashAttribute("mensagem", "Sala de atendimento salva com sucesso.");
		return new ModelAndView("redirect:/administracao/salasAtendimentos/nova");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoSalaAtendimento(@PathVariable("id") SalaAtendimento salaAtendimento) {
		ModelAndView mv = new ModelAndView(SALA_ATEND_CAD_VIEW);
		mv.addObject("todosTiposSalas", TipoSalaAtendimento.values());
		mv.addObject("todosStatusSalaAtendimento", StatusSalaAtendimento.values());
		mv.addObject(salaAtendimento);
		return mv;
	}
}