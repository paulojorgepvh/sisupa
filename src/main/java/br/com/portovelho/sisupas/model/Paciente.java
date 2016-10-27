package br.com.portovelho.sisupas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import br.com.portovelho.sisupas.enums.Sexo;


@Entity
public class Paciente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="O campo Nome Completo é obrigatório!")
    private String nomeCompleto;
    
    @NotBlank(message="O campo Nome da Mãe é obrigatório!")
    private String nomeDaMae;
    
    private String cpf;
    
    private String rg;
    
    private String orgaoExpedidor;
    
    private String cns;
    
    private String dataNascimento;
    @Transient
    private String idade;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToOne
    private Endereco endereco;

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

    public String getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
