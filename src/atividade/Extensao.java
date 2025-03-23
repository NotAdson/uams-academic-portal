package atividade;

import middleware.Validator;

/**
 * A classe {@code Extensao} representa uma atividade do tipo pesquisa e extensão, que é uma especialização da classe {@code Atividade}.
 */
public class Extensao extends Atividade{
	private String subtipo;

	public Extensao(String codigo, int tempo, String subtipo){
		super(codigo, "PESQUISA_EXTENSAO", 12 / 10, tempo);

		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(subtipo, "SUBTIPO");
		
		if(tempo < 0){
			throw new IllegalArgumentException("TEMPO MENOR QUE 0");
		}

		this.subtipo = subtipo;
	}

	public String getSubtipo(){
		return this.subtipo;
	}

	public void setSubtipo(String subtipo){
		this.subtipo = subtipo;
	}
}
