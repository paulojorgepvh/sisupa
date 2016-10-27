package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class CBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	MUDAR O NOME DA CONSTRAINT NO BANCO DE DADOS PARA: unique_codigo_cbo
	@NotNull(message="O campo código é obrigatório!")
	@Column(unique=true)
	private Long codigo;
	
//	MUDAR O NOME DA CONSTRAINT NO BANCO DE DADOS PARA: unique_descricao_cbo
	@NotBlank(message="O campo descrição é obrigatório!")
	@Column(unique=true)
	private String descricao;
	
	@ManyToMany(mappedBy = "cbos")
	private List<ProfissionalSaude> profissionais;
	
	private Boolean status;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		descricao = descricao.toUpperCase();
	}
	
	public CBO(){
		this.status = true;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProfissionalSaude> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<ProfissionalSaude> profissionais) {
		this.profissionais = profissionais;
	}

	public Long getCodigo() {
		return codigo;
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
		CBO other = (CBO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
