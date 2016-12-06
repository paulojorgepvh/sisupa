package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.portovelho.sisupas.enums.Sexo;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "cpf", name = "unique_paciente_cpf"),
		@UniqueConstraint(columnNames = "rg", name = "unique_paciente_rg"),
		@UniqueConstraint(columnNames = "cns", name = "unique_paciente_cns") })
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo Nome Completo é obrigatório!")
	private String nomeCompleto;

	@NotBlank(message = "O campo Nome da Mãe é obrigatório!")
	private String nomeDaMae;

	@NotBlank(message = "O campo CPF é obrigatório!")
	private String cpf;

	@NotBlank(message = "O campo RG é obrigatório!")
	private String rg;

	@NotBlank(message = "O campo Orgão Expedidor é obrigatório!")
	private String orgaoExpedidor;

	@NotBlank(message = "O campo CNS é obrigatório!")
	private String cns;
	
	private int idade;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A data de nascimento é obrigatória!")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O Sexo é obrigatório!")
	private Sexo sexo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@Valid
	private Endereco endereco;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		this.nomeCompleto = nomeCompleto.toUpperCase().trim();
		this.nomeDaMae = nomeDaMae.toUpperCase().trim();
		this.rg = rg.trim();
		this.orgaoExpedidor = orgaoExpedidor.toUpperCase().trim();
		this.cns = cns.trim();
		this.idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeDaMae() {
		return nomeDaMae;
	}

	public void setNomeDaMae(String nomedaMae) {
		this.nomeDaMae = nomedaMae;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int calculaIdade(){
		return Period.between(this.dataNascimento, LocalDate.now()).getYears();
	}
	
	public void InsereAtualizaIdade(){
		this.idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
