package br.com.portovelho.sisupas.repository.filter;

import br.com.portovelho.sisupas.model.Municipio;

public class BairroFiltro {
	private String nome;
	private Municipio municipio;
	

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
