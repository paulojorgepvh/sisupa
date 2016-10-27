package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity

public class AtendimentoIdentificado extends Atendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Paciente paciente;
	
	@OneToMany(mappedBy = "atendimentoIdentificado")
	private List<Triagem> triagens;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Triagem> getTriagens() {
		return triagens;
	}

	public void setTriagens(List<Triagem> triagens) {
		this.triagens = triagens;
	}

}
