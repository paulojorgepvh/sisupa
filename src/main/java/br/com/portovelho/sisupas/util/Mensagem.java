package br.com.portovelho.sisupas.util;

import org.springframework.stereotype.Service;

@Service
public abstract class Mensagem {
	
	private String mensagem;
	private Boolean erro;
	
	public Mensagem() {
		this.erro = false;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}
}
