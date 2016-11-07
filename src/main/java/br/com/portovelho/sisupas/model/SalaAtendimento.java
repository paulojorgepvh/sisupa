package br.com.portovelho.sisupas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.portovelho.sisupas.enums.StatusSalaAtendimento;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;

@Entity
@SequenceGenerator(name = SalaAtendimento.SEQUENCE_NAME, sequenceName = SalaAtendimento.SEQUENCE_NAME, initialValue = 0, allocationSize = 50)
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"descricao", "numero","tipoSalaAtendimento"})
	}) 
public class SalaAtendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "SEQUENCIA_SalaAtendimento";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Long id;
	
	@NotBlank(message = "O campo Descrição é obrigatório!")
	private String descricao;
	
	@NotNull(message = "O campo Número é obrigatório!")
	private Integer numero;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo Tipo da Sala é obrigatório!")
	private TipoSalaAtendimento tipoSalaAtendimento;
		
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo Status da Sala é obrigatório!")
	private StatusSalaAtendimento statusSalaAtendimento;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		descricao = descricao.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoSalaAtendimento getTipoSalaAtendimento() {
		return tipoSalaAtendimento;
	}

	public void setTipoSalaAtendimento(TipoSalaAtendimento tipoSalaAtendimento) {
		this.tipoSalaAtendimento = tipoSalaAtendimento;
	}

	public StatusSalaAtendimento getStatusSalaAtendimento() {
		return statusSalaAtendimento;
	}

	public void setStatusSalaAtendimento(StatusSalaAtendimento statusSalaAtendimento) {
		this.statusSalaAtendimento = statusSalaAtendimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaAtendimento other = (SalaAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
