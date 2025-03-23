package dica;

import middleware.Validator;

public class Texto implements IAnexo{
	private String texto;

	public Texto(String texto){
		Validator.verifyStringBlank(texto, "TEXTO");

		this.texto = texto;
	}

	public String getResumo(){
		return this.texto;
	}

	public String getDetalhado(){
		return this.texto + "\n" + this.texto.length();
	}

	public double calcularBonus(){
		if(this.texto.length() < 100){
			return 0;
		}

		return this.texto.length() / 10;
	}
}
