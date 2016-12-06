package br.com.portovelho.sisupas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.portovelho.sisupas.enums.Estrutura;
import br.com.portovelho.sisupas.enums.PorteFisico;
import br.com.portovelho.sisupas.enums.Raca;
import br.com.portovelho.sisupas.enums.Sexo;
import br.com.portovelho.sisupas.enums.TipoIdade;



@Entity
public class AtendimentoNaoIdentificado extends Atendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo Sexo é obrigatório!")
	private Sexo sexo;
	
	@NotNull(message = "O campo Faixa Etária Inicial é obrigatório!")
	private Integer faixaEtariaInicial;
	
	@NotNull(message = "O campo Faixa Etária Final é obrigatório!")
	private Integer faixaEtariaFinal;
	
	@Enumerated(EnumType.STRING)
	private TipoIdade tipoIdade;
	
	@Enumerated(EnumType.STRING)
	private Raca raca;
	
	@Enumerated(EnumType.STRING)
	private Estrutura estrutura;
	
	@Enumerated(EnumType.STRING)
	private PorteFisico porteFisico;

	@OneToOne
	@NotNull(message = "O campo Município é obrigatório!")
	private Municipio municipio;

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getFaixaEtariaInicial() {
		return faixaEtariaInicial;
	}

	public void setFaixaEtariaInicial(Integer faixaEtariaInicial) {
		this.faixaEtariaInicial = faixaEtariaInicial;
	}

	public Integer getFaixaEtariaFinal() {
		return faixaEtariaFinal;
	}

	public void setFaixaEtariaFinal(Integer faixaEtariaFinal) {
		this.faixaEtariaFinal = faixaEtariaFinal;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Estrutura getEstrutura() {
		return estrutura;
	}

	public void setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
	}

	public PorteFisico getPorteFisico() {
		return porteFisico;
	}

	public void setPorteFisico(PorteFisico porteFisico) {
		this.porteFisico = porteFisico;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public TipoIdade getTipoIdade() {
		return tipoIdade;
	}

	public void setTipoIdade(TipoIdade tipoIdade) {
		this.tipoIdade = tipoIdade;
	}
}
