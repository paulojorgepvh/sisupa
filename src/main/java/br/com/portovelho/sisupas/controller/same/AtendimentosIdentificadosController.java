package br.com.portovelho.sisupas.controller.same;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.model.AtendimentoIdentificado;
import br.com.portovelho.sisupas.repository.AtendimentosIdentificadosRepository;
import br.com.portovelho.sisupas.repository.filter.AtendimentoIdentificadoFiltro;

@Controller
@RequestMapping("/same/atendimentosIdentificados")
public class AtendimentosIdentificadosController {
	
	private static final String ATEND_IDENT_CAD_VIEW = "/same/atendimentoIdentificado/CadastroAtendimentoIdentificado";
	private static final String ATEND_IDENT_PESQ_VIEW = "/same/atendimentoIdentificado/PesquisaAtendimentoIdentificado";
//	private static final String ATEND_IDENT_DETALHES_VIEW = "/same/atendimentoIdentificado/DetalhesAtendimentoIdentificado";

	@Autowired
	private AtendimentosIdentificadosRepository atendimentosIdentificadosRepository;

//	@Autowired
//	private AtendimentoIdentificadoService atendimentoIdentificadoService;
//	
	@GetMapping
	public ModelAndView listaAtendimentosIdentificados(@ModelAttribute("filtro") AtendimentoIdentificadoFiltro filtro,
			@PageableDefault(size = 25) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(ATEND_IDENT_PESQ_VIEW);

		PageWrapper<AtendimentoIdentificado> paginaWrapper = new PageWrapper<>(atendimentosIdentificadosRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoAtendimentoIdentificado(AtendimentoIdentificado atendimentoIdentificado) {
		ModelAndView mv = new ModelAndView(ATEND_IDENT_CAD_VIEW);
		return mv;
	}

//	@PostMapping("/novo")
//	public ModelAndView salvarAtendimentoIdentificado(@Valid AtendimentoIdentificado atendimentoIdentificado, BindingResult result, Model model,
//			RedirectAttributes attributes) {
//		if (result.hasErrors()) {
//			return novoAtendimentoIdentificado(atendimentoIdentificado);
//		}
//		try {
//			atendimentoIdentificadoService.salvar(atendimentoIdentificado);
//			attributes.addFlashAttribute("mensagem", "Atendimento Identificado salvo com sucesso!");
//		} catch (ConstraintViolationException e) {
//			if (e.getConstraintName().equals("unique_paciente_cpf")) {
//				result.rejectValue("cpf", null, "CPF já cadastrado!");
//			}
//			if (e.getConstraintName().equals("unique_paciente_rg")) {
//				result.rejectValue("rg", null, "CPF já cadastrado!");
//			}
//			if (e.getConstraintName().equals("unique_paciente_cns")) {
//				result.rejectValue("cns", null, "CPF já cadastrado!");
//			}
//			return novoAtendimentoIdentificado(atendimentoIdentificado);
//		}
//		return new ModelAndView("redirect:/same/atendimentosIdentificados/novo");
//	}
}
