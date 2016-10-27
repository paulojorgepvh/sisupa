package br.com.portovelho.sisupas.controller.administracao;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.enums.PerfilDeAcesso;
import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.CbosRepository;
import br.com.portovelho.sisupas.repository.ProfissionaisSaudeRepository;
import br.com.portovelho.sisupas.repository.filter.ProfissionalSaudeFiltro;
import br.com.portovelho.sisupas.service.ProfissionalSaudeService;

@Controller
@RequestMapping("/administracao/profissionalSaude")
public class ProfissionaisSaudeController {

	private static final String PROF_CADASTRO_VIEW = "/administracao/profissionalSaude/CadastroProfissionalSaude";
	private static final String PROF_PESQUISA_VIEW = "/administracao/profissionalSaude/PesquisaProfissionalSaude";

	@Autowired
	private CbosRepository cboRepository;

	@Autowired
	private ProfissionalSaudeService profissionalSaudeService;

	@Autowired
	private ProfissionaisSaudeRepository profissionaisSaudeRepository;

	@ModelAttribute("todosCbos")
	public List<CBO> todosCbos() {
		return cboRepository.findAllByOrderByDescricaoAsc();
	}

	@ModelAttribute("todosPerfisDeAcesso")
	public List<PerfilDeAcesso> todosPerfisDeAcesso() {
		return Arrays.asList(PerfilDeAcesso.values());
	}

	@GetMapping
	public ModelAndView listaProfissionaisSaude(@ModelAttribute("filtro") ProfissionalSaudeFiltro filtro,
			BindingResult result, @PageableDefault(size = 1) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PROF_PESQUISA_VIEW);
		
		PageWrapper<ProfissionalSaude> paginaWrapper = new PageWrapper<>(profissionaisSaudeRepository.filtrar(filtro,pageable),httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoProfissionalSaude() {
		ModelAndView mv = new ModelAndView(PROF_CADASTRO_VIEW);
		mv.addObject(new ProfissionalSaude());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvarProfissionalSaude(@Validated ProfissionalSaude profissionalSaude, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return PROF_CADASTRO_VIEW;
		}
		profissionalSaudeService.salvar(profissionalSaude);
		attributes.addFlashAttribute("mensagem", "Profissional de Saúde salvo com sucesso!");
		return "redirect:/administracao/profissionalSaude/novo";
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoProfissionalSaude(@PathVariable("id") ProfissionalSaude profissionalSaude) {
		ModelAndView mv = new ModelAndView(PROF_CADASTRO_VIEW);
		mv.addObject(profissionalSaude);
		return mv;
	}

	@RequestMapping("/login/{id}")
	public ModelAndView edicaoLoginProfissionalSaude(@PathVariable("id") ProfissionalSaude profissionalSaude) {
		ModelAndView mv = new ModelAndView("/EditarLoginProfissionalSaude");
		mv.addObject(profissionalSaude);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusProfissional(@PathVariable Long id) {
		return profissionalSaudeService.mudarStatus(id);
	}
}
