package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "codigo", name = "unique_codigo"), @UniqueConstraint(columnNames = "descricao", name = "unique_descricao") })
public class ProcedimentoInterno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo Código é obrigatório!")
	private String codigo;

	@NotBlank(message = "O campo Descrição é obrigatório!")
	private String descricao;

	@ElementCollection(targetClass = TipoSalaAtendimento.class)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo Tipo de Sala é obrigatório!")
	private List<TipoSalaAtendimento> tipoSalaAtendimento;

	private Boolean status;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		descricao = descricao.toUpperCase();
	}
	
	public ProcedimentoInterno() {
		this.status = true;
	}

	public String getCodigo() {
		return codigo;
	}

	public Long getId() {
		return id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<TipoSalaAtendimento> getTipoSalaAtendimento() {
		return tipoSalaAtendimento;
	}

	public void setTipoSalaAtendimento(List<TipoSalaAtendimento> tipoSalaAtendimento) {
		this.tipoSalaAtendimento = tipoSalaAtendimento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		ProcedimentoInterno other = (ProcedimentoInterno) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
