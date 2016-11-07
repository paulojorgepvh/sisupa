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
import br.com.portovelho.sisupas.model.Bairro;
import br.com.portovelho.sisupas.repository.BairrosRepository;
import br.com.portovelho.sisupas.repository.UfsRepository;
import br.com.portovelho.sisupas.repository.filter.BairroFiltro;
import br.com.portovelho.sisupas.service.BairroService;
import br.com.portovelho.sisupas.service.CadastroBairroService;
import br.com.portovelho.sisupas.service.exception.NomeBairroJaCadastradoException;

@Controller
@RequestMapping("/administracao/bairros")
public class BairrosController {

	private static final String BAIRRO_CAD_VIEW = "/administracao/bairro/CadastroBairro";
	private static final String BAIRRO_PESQUISA_VIEW = "/administracao/bairro/PesquisaBairro";

	@Autowired
	private BairroService bairroService;

	@Autowired
	private CadastroBairroService cadastroBairroService;

	@Autowired
	private UfsRepository ufsRepository;

	@Autowired
	private BairrosRepository bairrosRepository;

	@GetMapping
	public ModelAndView listaBairro(@ModelAttribute("filtro") BairroFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(BAIRRO_PESQUISA_VIEW);
		mv.addObject("todosUfs", ufsRepository.findAllByOrderByNome());

		PageWrapper<Bairro> paginaWrapper = new PageWrapper<>(bairrosRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoBairro(Bairro bairro) {
		ModelAndView mv = new ModelAndView(BAIRRO_CAD_VIEW);
		mv.addObject("todosUfs", ufsRepository.findAllByOrderByNome());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvarBairro(@Valid Bairro bairro, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoBairro(bairro);
		}
		try {
			cadastroBairroService.salvar(bairro);
		} catch (NomeBairroJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novoBairro(bairro);
		}
		attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso!");
		return new ModelAndView("redirect:/administracao/bairros/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoBairro(@PathVariable("id") Bairro bairro) {
		ModelAndView mv = new ModelAndView(BAIRRO_CAD_VIEW);
		mv.addObject("todosUfs", ufsRepository.findAllByOrderByNome());
		mv.addObject(bairro);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusBairro(@PathVariable Long id) {
		return bairroService.mudarStatus(id);
	}
}
