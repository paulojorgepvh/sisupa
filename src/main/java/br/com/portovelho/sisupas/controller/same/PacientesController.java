package br.com.portovelho.sisupas.controller.same;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.portovelho.sisupas.controller.page.PageWrapper;
import br.com.portovelho.sisupas.enums.Sexo;
import br.com.portovelho.sisupas.model.Paciente;
import br.com.portovelho.sisupas.model.UF;
import br.com.portovelho.sisupas.repository.PacientesRepository;
import br.com.portovelho.sisupas.repository.UfsRepository;
import br.com.portovelho.sisupas.repository.filter.PacienteFiltro;
import br.com.portovelho.sisupas.service.PacienteService;

@Controller
@RequestMapping("/same/pacientes")
public class PacientesController {

	private static final String PACIENTE_CAD_VIEW = "/same/paciente/CadastroPaciente";
	private static final String PACIENTE_PESQ_VIEW = "/same/paciente/PesquisaPaciente";
	private static final String PACIENTE_DETALHES_VIEW = "/same/paciente/DetalhesPaciente";

	@Autowired
	private PacientesRepository pacientesRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private UfsRepository ufRepository;

	@ModelAttribute("todosSexos")
	public List<Sexo> todosSexos() {
		return Arrays.asList(Sexo.values());
	}

	@ModelAttribute("todasUfs")
	public List<UF> todasUfs() {
		return ufRepository.findAllByOrderByNomeAsc();
	}

	@GetMapping
	public ModelAndView listaPacientes(@ModelAttribute("filtro") PacienteFiltro filtro,
			@PageableDefault(size = 25) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PACIENTE_PESQ_VIEW);

		PageWrapper<Paciente> paginaWrapper = new PageWrapper<>(pacientesRepository.filtrar(filtro, pageable),
				httpServletRequest);

		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novoPaciente(Paciente paciente) {
		ModelAndView mv = new ModelAndView(PACIENTE_CAD_VIEW);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvarPaciente(@Valid Paciente paciente, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoPaciente(paciente);
		}
		try {
			pacienteService.salvar(paciente);
			attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso!");
		} catch (ConstraintViolationException e) {
			if (e.getConstraintName().equals("unique_paciente_cpf")) {
				result.rejectValue("cpf", null, "CPF já cadastrado!");
			}
			if (e.getConstraintName().equals("unique_paciente_rg")) {
				result.rejectValue("rg", null, "CPF já cadastrado!");
			}
			if (e.getConstraintName().equals("unique_paciente_cns")) {
				result.rejectValue("cns", null, "CPF já cadastrado!");
			}
			return novoPaciente(paciente);
		}
		return new ModelAndView("redirect:/same/pacientes/novo");
	}
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhesPaciente(@PathVariable("id") Long id){
		ModelAndView mv = new ModelAndView(PACIENTE_DETALHES_VIEW);
		mv.addObject("paciente", pacientesRepository.findOne(id));
		return mv;
	}
}
