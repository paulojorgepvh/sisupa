package br.com.portovelho.sisupas.controller.administracao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.model.Municipio;
import br.com.portovelho.sisupas.repository.MunicipiosRepository;
import br.com.portovelho.sisupas.repository.UfsRepository;
import br.com.portovelho.sisupas.repository.filter.MunicipioFiltro;
import br.com.portovelho.sisupas.service.CadastroMunicipioService;
import br.com.portovelho.sisupas.service.exception.NomeMunicipioJaCadastradoException;

@Controller
@RequestMapping("/administracao/municipios")
public class MunicipiosController {

	private static final String MUNICIPIO_CAD_VIEW = "/administracao/municipio/CadastroMunicipio";
	private static final String MUNICIPIO_PESQUISA_VIEW = "/administracao/municipio/PesquisaMunicipio";

	@Autowired
	private UfsRepository ufsRepository;

	@Autowired
	private MunicipiosRepository municipiosRepository;

	@Autowired
	private CadastroMunicipioService cadastroMunicipioService;

	@GetMapping
	public ModelAndView listaMunicipios(@ModelAttribute("filtro") MunicipioFiltro filtro,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(MUNICIPIO_PESQUISA_VIEW);

		PageWrapper<Municipio> paginaWrapper = new PageWrapper<>(municipiosRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoMunicipio(Municipio municipio) {
		ModelAndView mv = new ModelAndView(MUNICIPIO_CAD_VIEW);
		mv.addObject("todosUfs", ufsRepository.findAllByOrderByNomeAsc());
		return mv;
	}

	@PostMapping("/novo")
	@CacheEvict(value = "municipios", key = "#municipio.uf.id", condition = "#municipio.temUf()")
	public ModelAndView salvarMunicipio(@Valid Municipio municipio, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoMunicipio(municipio);
		}
		try {
			cadastroMunicipioService.salvar(municipio);
		} catch (NomeMunicipioJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novoMunicipio(municipio);
		}
		attributes.addFlashAttribute("mensagem", "Município salvo com sucesso!");
		return new ModelAndView("redirect:/administracao/municipios/novo");
	}

	@RequestMapping("/{id}")
	public ModelAndView edicaoMunicipio(@PathVariable("id") Municipio municipio) {
		ModelAndView mv = new ModelAndView(MUNICIPIO_CAD_VIEW);
		mv.addObject(municipio);
		return mv;
	}

	@Cacheable(value = "municipios", key = "#uf")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Municipio> pesquisarPorIdUf(@RequestParam(name = "uf", defaultValue = "-1") Long uf) {
		List<Municipio> findByUfId = municipiosRepository.findByUfId(uf);

		return findByUfId;
	}

}
