package br.com.portovelho.sisupas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Triagem implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name = "atendimentoIdentificado_id")
	private AtendimentoIdentificado atendimentoIdentificado;
}
