package atividade;

import middleware.Validator;

public class Estagio extends Atividade{
	private String empresa;

	public Estagio(String codigo, int tempo, String empresa){
		super(codigo, "ESTAGIO", 60 / 1, tempo);

		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(empresa, "EMPRESA");
		
		if(tempo < 0){
			throw new IllegalArgumentException("TEMPO MENOR QUE 0");
		}

		this.empresa = empresa;
	}

	public String getEmpresa(){
		return this.empresa;
	}

	public void setEmpresa(String empresa){
		this.empresa = empresa;
	}
}
