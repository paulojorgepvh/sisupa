package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.portovelho.sisupas.enums.PerfilDeAcesso;

@Entity
public class ProfissionalSaude implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo Nome completo é obrigatório!")
	private String nomeCompleto;

	@NotBlank(message = "O campo CPF é obrigatório!")
	@CPF
	private String cpf;

	@NotBlank(message = "O campo Telefone é obrigatório!")
	private String telefone;;

	private Boolean ativo;

	@ManyToMany
	@JoinTable(name = "profissionais_cbos")
	@NotNull(message = "O campo CBO é obrigatório!")
	private List<CBO> cbos;

	@NotBlank(message = "O campo Login é obrigatório!")
	@Size(min = 4, message = "O login não pode ser menor que 4 caracteres!")
	private String login;

	@NotBlank(message = "O campo Senha é obrigatório!")
	@Size(min = 4, message = "A senha não pode ser menor que 4 caracteres!")
	private String senha;

	@Enumerated(EnumType.STRING)
	private PerfilDeAcesso perfilDeAcesso;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nomeCompleto = nomeCompleto.toUpperCase();
	}

	public ProfissionalSaude() {
		this.ativo = true;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<CBO> getCbos() {
		return cbos;
	}

	public void setCbos(List<CBO> cbos) {
		this.cbos = cbos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilDeAcesso getPerfilDeAcesso() {
		return perfilDeAcesso;
	}

	public void setPerfilDeAcesso(PerfilDeAcesso perfilDeAcesso) {
		this.perfilDeAcesso = perfilDeAcesso;
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
		ProfissionalSaude other = (ProfissionalSaude) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
