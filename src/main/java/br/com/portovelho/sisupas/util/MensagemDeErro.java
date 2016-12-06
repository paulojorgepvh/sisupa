package br.com.portovelho.sisupas.util;

import org.springframework.stereotype.Service;

@Service
public class MensagemDeErro extends Mensagem{

	private String propriedade;
	
	public String getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}
}
