package br.com.portovelho.sisupas.controller.administracao;

import java.util.Arrays;
import java.util.List;

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
import br.com.portovelho.sisupas.enums.PerfilDeAcesso;
import br.com.portovelho.sisupas.model.CBO;
import br.com.portovelho.sisupas.model.ProfissionalSaude;
import br.com.portovelho.sisupas.repository.CbosRepository;
import br.com.portovelho.sisupas.repository.ProfissionaisSaudeRepository;
import br.com.portovelho.sisupas.repository.filter.ProfissionalSaudeFiltro;
import br.com.portovelho.sisupas.service.ProfissionalSaudeService;

@Controller
@RequestMapping("/administracao/profissionaisSaude")
public class ProfissionaisSaudeController {

	private static final String PROF_CADASTRO_VIEW = "/administracao/profissionalSaude/CadastroProfissionalSaude";
	private static final String PROF_PESQUISA_VIEW = "/administracao/profissionalSaude/PesquisaProfissionalSaude";
	private static final String LOGIN_EDIT_VIEW = "/administracao/profissionalSaude/EditarLoginProfissionalSaude";

	@Autowired
	private CbosRepository cboRepository;

	@Autowired
	private ProfissionalSaudeService profissionalSaudeService;

	@Autowired
	private ProfissionaisSaudeRepository profissionaisSaudeRepository;

	@ModelAttribute("todosCbos")
	public List<CBO> todosCbos() {
		return cboRepository.findAllByStatusTrueOrderByDescricaoAsc();
	}

	@ModelAttribute("todosPerfisDeAcesso")
	public List<PerfilDeAcesso> todosPerfisDeAcesso() {
		return Arrays.asList(PerfilDeAcesso.values());
	}

	@GetMapping
	public ModelAndView listaProfissionaisSaude(@ModelAttribute("filtro") ProfissionalSaudeFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PROF_PESQUISA_VIEW);
		
		PageWrapper<ProfissionalSaude> paginaWrapper = new PageWrapper<>(profissionaisSaudeRepository.filtrar(filtro,pageable),httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novoProfissionalSaude(ProfissionalSaude profissionalSaude) {
		ModelAndView mv = new ModelAndView(PROF_CADASTRO_VIEW);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvarProfissionalSaude(@Valid ProfissionalSaude profissionalSaude, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoProfissionalSaude(profissionalSaude);
		}
		try {
			profissionalSaudeService.salvar(profissionalSaude);
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_cpf")) {
				result.rejectValue("cpf", null, "CPF já cadastrado!");
			}
			return novoProfissionalSaude(profissionalSaude);
		}
			attributes.addFlashAttribute("mensagem", "Profissional de Saúde salvo com sucesso!");
			return new ModelAndView("redirect:/administracao/profissionaisSaude/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoProfissionalSaude(@PathVariable("id") ProfissionalSaude profissionalSaude) {
		ModelAndView mv = new ModelAndView(PROF_CADASTRO_VIEW);
		mv.addObject(profissionalSaude);
		return mv;
	}

	@RequestMapping("/login/{id}")
	public ModelAndView edicaoLoginProfissionalSaude(@PathVariable("id") ProfissionalSaude profissionalSaude) {
		ModelAndView mv = new ModelAndView(LOGIN_EDIT_VIEW);
		mv.addObject(profissionalSaude);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusProfissional(@PathVariable Long id) {
		return profissionalSaudeService.mudarStatus(id);
	}
}
