package atividade;

import middleware.Validator;

public class RepresentacaoEstudantil extends Atividade{
	private String subTipo;

	public RepresentacaoEstudantil(String codigo, int tempo, String subTipo){
		super(codigo, "REPRESENTACAO_ESTUDANTIL", 2, tempo);

		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(subTipo, "SUBTIPO");
		
		if(tempo < 0){
			throw new IllegalArgumentException("TEMPO MENOR QUE 0");
		}

		this.subTipo = subTipo;
	}

	public String getSubTipo(){
		return this.subTipo;
	}

	public void setSubTipo(String subTipo){
		this.subTipo = subTipo;
	}
}
