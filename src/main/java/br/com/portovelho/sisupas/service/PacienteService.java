package br.com.portovelho.sisupas.service;

import java.time.LocalDate;
import java.time.Period;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.portovelho.sisupas.model.Paciente;
import br.com.portovelho.sisupas.repository.PacientesRepository;

@Service
public class PacienteService {

	@Autowired
	private PacientesRepository pacientesRepository;

	public void salvar(Paciente paciente) {
		try {
			pacientesRepository.save(paciente);
		} catch (DataIntegrityViolationException e) {
			throw (ConstraintViolationException) e.getCause();
		}

	}
	
	public static int idade(final LocalDate dataNascimento) {
	    return Period.between(dataNascimento, LocalDate.now()).getYears();
	}

}
