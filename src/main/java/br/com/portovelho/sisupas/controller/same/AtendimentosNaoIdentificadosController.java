package br.com.portovelho.sisupas.controller.same;

import java.util.Arrays;
import java.util.List;

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
import br.com.portovelho.sisupas.enums.DetalhesDoAtendimento;
import br.com.portovelho.sisupas.enums.Estrutura;
import br.com.portovelho.sisupas.enums.PorteFisico;
import br.com.portovelho.sisupas.enums.Raca;
import br.com.portovelho.sisupas.enums.Sexo;
import br.com.portovelho.sisupas.enums.TipoIdade;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;
import br.com.portovelho.sisupas.model.AtendimentoNaoIdentificado;
import br.com.portovelho.sisupas.model.MotivoAtendimento;
import br.com.portovelho.sisupas.model.UF;
import br.com.portovelho.sisupas.repository.AtendimentosNaoIdentificadosRepository;
import br.com.portovelho.sisupas.repository.MotivosAtendimentosRepository;
import br.com.portovelho.sisupas.repository.UfsRepository;
import br.com.portovelho.sisupas.repository.filter.AtendimentoNaoIdentificadoFiltro;
import br.com.portovelho.sisupas.service.AtendimentoNaoIdentificadoService;

@Controller
@RequestMapping("/same/atendimentosNaoIdentificados")
public class AtendimentosNaoIdentificadosController {

	private static final String ATEND_NAO_IDENT_CAD_VIEW = "/same/atendimentoNaoIdentificado/CadastroAtendimentoNaoIdentificado";
	private static final String ATEND_NAO_IDENT_PESQ_VIEW = "/same/atendimentoNaoIdentificado/PesquisaAtendimentoNaoIdentificado";
	
	@Autowired
	private AtendimentosNaoIdentificadosRepository atendimentosNaoIdentificadosRepository;
	
	@Autowired
	private MotivosAtendimentosRepository motivosAtendimentosRepository;
	
	@Autowired
	private UfsRepository ufRepository;
	
	@Autowired
	private AtendimentoNaoIdentificadoService atendimentoNaoIdentificadoService;
	
	@ModelAttribute("todasEstruturas")
	public List<Estrutura> todasEstruturas() {
		return Arrays.asList(Estrutura.values());
	}
	
	@ModelAttribute("todosPorteFisicos")
	public List<PorteFisico> todosPorteFisicos() {
		return Arrays.asList(PorteFisico.values());
	}
	
	@ModelAttribute("todasRacas")
	public List<Raca> todasRacas() {
		return Arrays.asList(Raca.values());
	}
	
	@ModelAttribute("todosSexos")
	public List<Sexo> todosSexos() {
		return Arrays.asList(Sexo.values());
	}
	
	@ModelAttribute("todosTipoIdades")
	public List<TipoIdade> todosTipoIdades() {
		return Arrays.asList(TipoIdade.values());
	}
	
	@ModelAttribute("todasUfs")
	public List<UF> todasUfs() {
		return ufRepository.findAllByOrderByNomeAsc();
	}
	
	@ModelAttribute("todosMotivosAtendimento")
	public List<MotivoAtendimento> todosMotivosAtendimento() {
		return motivosAtendimentosRepository.findAllByStatusTrueOrderByDescricaoAsc();
	}
	
	@ModelAttribute("todosTipoSalaAtendimento")
	public List<TipoSalaAtendimento> todosTipoSalaAtendimento() {
		return Arrays.asList(TipoSalaAtendimento.values());
	}
	
	@ModelAttribute("todosDetalhesAtendimento")
	public List<DetalhesDoAtendimento> todosDetalhesAtendimento() {
		return Arrays.asList(DetalhesDoAtendimento.values());
	}
	
	@GetMapping
	public ModelAndView listaAtendimentoNaoIdentificado(@ModelAttribute("filtro") AtendimentoNaoIdentificadoFiltro filtro,
			@PageableDefault(size = 25) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(ATEND_NAO_IDENT_PESQ_VIEW);
		
		PageWrapper<AtendimentoNaoIdentificado> paginaWrapper = new PageWrapper<>(atendimentosNaoIdentificadosRepository.filtrar(filtro,pageable),httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novoAtendimentoNaoIdentificado(AtendimentoNaoIdentificado atendimentoNaoIdentificado) {
		ModelAndView mv = new ModelAndView(ATEND_NAO_IDENT_CAD_VIEW);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvarAtendimentoNaoIdentificado(@Valid AtendimentoNaoIdentificado atendimentoNaoIdentificado, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoAtendimentoNaoIdentificado(atendimentoNaoIdentificado);
		}
		try {
			atendimentoNaoIdentificadoService.salvar(atendimentoNaoIdentificado);
			attributes.addFlashAttribute("mensagem", "Atendimento não identificado salvo com sucesso!");
			return new ModelAndView("redirect:/same/atendimentosNaoIdentificados/novo");
		} catch (RuntimeException e) {
			result.rejectValue(null, e.getMessage(), e.getMessage());
			return novoAtendimentoNaoIdentificado(atendimentoNaoIdentificado);
		}
	}
	
	@RequestMapping("/{id}")
	public ModelAndView edicaoAtendimentoNaoIdentificado(@PathVariable("id") AtendimentoNaoIdentificado atendimentoNaoIdentificado) {
		ModelAndView mv = new ModelAndView(ATEND_NAO_IDENT_CAD_VIEW);
		mv.addObject(atendimentoNaoIdentificado);
		return mv;
	}

	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudaStatusAtendimentoNaoIdentificado(@PathVariable Long id) {
		return atendimentoNaoIdentificadoService.mudarStatus(id);
	}
}
