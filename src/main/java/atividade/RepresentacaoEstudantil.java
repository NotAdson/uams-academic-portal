package atividade;

import middleware.Validator;

/**
 * A classe {@code RepresentacaoEstudantil} representa uma atividade do tipo representação estudantil, que é uma especialização da classe {@code Atividade}.
 */
public class RepresentacaoEstudantil extends Atividade{
	private String subTipo;

	public RepresentacaoEstudantil(String codigo, int tempo, String subTipo){
		super(codigo, "REPRESENTACAO_ESTUDANTIL", 2, tempo);

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
