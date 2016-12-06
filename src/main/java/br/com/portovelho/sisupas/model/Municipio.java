package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Municipio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo Código IBGE é obrigatório!")
	private Long codigoIbge;
	
	@NotBlank(message = "O campo nome é obrigatório!")
	private String nome;
	
	@NotNull(message = "O campo UF é obrigatório!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uf_id")
	@JsonIgnore
	private UF uf;
	
	@OneToMany(mappedBy = "municipio")
	private List<Bairro> bairros;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nome = nome.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setCodigoIbge(Long codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public Long getCodigoIbge() {
		return codigoIbge;
	}
	
	public boolean temUf(){
		return uf != null;
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
		Municipio other = (Municipio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
