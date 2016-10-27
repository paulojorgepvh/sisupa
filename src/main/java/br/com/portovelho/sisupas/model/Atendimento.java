package br.com.portovelho.sisupas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.portovelho.sisupas.enums.DetalhesDoAtendimento;
import br.com.portovelho.sisupas.enums.StatusAtendimento;
import br.com.portovelho.sisupas.enums.TipoSalaAtendimento;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = Atendimento.SEQUENCE_NAME, sequenceName = Atendimento.SEQUENCE_NAME, initialValue = 0, allocationSize = 50)
public abstract class Atendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE_NAME = "SEQUENCIA_Atendimento";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date criadoEm;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date finalizadoEm;

	@NotBlank(message = "O campo senha é obrigatório!")
	private String senha;

	@Enumerated(EnumType.STRING)
	private TipoSalaAtendimento tipoSalaAtendimento;

	/*@Enumerated(EnumType.STRING)
	private TipoEspecialidadeSalaAtendimento tipoEspecialidadeSalaAtendimento;*/

	private Boolean emergencia;

	@Enumerated(EnumType.STRING)
	private StatusAtendimento statusAtendimento;

	@OneToOne
	private MotivoAtendimento motivoAtendimento;

	private String procedenciaDoFato;

	@ElementCollection(targetClass = DetalhesDoAtendimento.class)
	@Enumerated(EnumType.STRING)
	private List<DetalhesDoAtendimento> detalhesDoAtendimento;

	private String responsavelPaciente;

	private String telefoneResponsavelPaciente;

	public Atendimento() {
		this.emergencia = false;
	}

	public Long getId() {
		return id;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getFinalizadoEm() {
		return finalizadoEm;
	}

	public void setFinalizadoEm(Date finalizadoEm) {
		this.finalizadoEm = finalizadoEm;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoSalaAtendimento getTipoSalaAtendimento() {
		return tipoSalaAtendimento;
	}

	public void setTipoSalaAtendimento(TipoSalaAtendimento tipoSalaAtendimento) {
		this.tipoSalaAtendimento = tipoSalaAtendimento;
	}

	/*public TipoEspecialidadeSalaAtendimento getTipoEspecialidadeSalaAtendimento() {
		return tipoEspecialidadeSalaAtendimento;
	}

	public void setTipoEspecialidadeSalaAtendimento(TipoEspecialidadeSalaAtendimento tipoEspecialidadeSalaAtendimento) {
		this.tipoEspecialidadeSalaAtendimento = tipoEspecialidadeSalaAtendimento;
	}*/

	public Boolean getEmergencia() {
		return emergencia;
	}

	public void setEmergencia(Boolean emergencia) {
		this.emergencia = emergencia;
	}

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public MotivoAtendimento getMotivoAtendimento() {
		return motivoAtendimento;
	}

	public void setMotivoAtendimento(MotivoAtendimento motivoAtendimento) {
		this.motivoAtendimento = motivoAtendimento;
	}

	public String getProcedenciaDoFato() {
		return procedenciaDoFato;
	}

	public void setProcedenciaDoFato(String procedenciaDoFato) {
		this.procedenciaDoFato = procedenciaDoFato;
	}

	public List<DetalhesDoAtendimento> getDetalhesDoAtendimento() {
		return detalhesDoAtendimento;
	}

	public void setDetalhesDoAtendimento(List<DetalhesDoAtendimento> detalhesDoAtendimento) {
		this.detalhesDoAtendimento = detalhesDoAtendimento;
	}

	public String getResponsavelPaciente() {
		return responsavelPaciente;
	}

	public void setResponsavelPaciente(String responsavelPaciente) {
		this.responsavelPaciente = responsavelPaciente;
	}

	public String getTelefoneResponsavelPaciente() {
		return telefoneResponsavelPaciente;
	}

	public void setTelefoneResponsavelPaciente(String telefoneResponsavelPaciente) {
		this.telefoneResponsavelPaciente = telefoneResponsavelPaciente;
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
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
