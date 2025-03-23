package dica;

import middleware.Validator;

public class Multimidia implements IAnexo{
	private String link, cabecalho;
	private int duracao;

	public Multimidia(String link, String cabecalho, int duracao){
		Validator.verifyStringBlank(link, "LINK");
		Validator.verifyStringBlank(cabecalho, "CABECALHO");

		this.link = link;
		this.cabecalho = cabecalho;
		this.duracao = duracao;
	}

	public String getResumo(){
		return this.link + "\n" + this.cabecalho;
	}

	public String getDetalhado(){
		return this.link + "\n" + this.duracao + "s\n" + this.cabecalho;
	}

	public double calcularBonus(){
		return (this.duracao / 60) * 5;
	}
}
