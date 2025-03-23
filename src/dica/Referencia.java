package dica;

import middleware.Validator;

public class Referencia implements IAnexo{
	private String fonte, titulo;
	private boolean verificado;
	private int ano, relevancia;

	public Referencia(String titulo, String fonte, boolean verificado, int ano, int relevancia){
		Validator.verifyStringBlank(titulo, "TITULO");
		Validator.verifyStringBlank(fonte, "FONTE");

		this.titulo = titulo;
		this.fonte = fonte;
		this.verificado = verificado;
		this.ano = ano;
		this.relevancia = relevancia;
	}

	public String getResumo(){
		return this.titulo + "\n" + this.fonte + "\n" + this.ano;
	}

	public String getDetalhado(){
		return this.titulo + "\n" + this.fonte + "\n" + this.relevancia + "/5\n" + "verificado: "+ (this.verificado ? "Sim\n":"Não\n") + this.ano;
	}

	public double calcularBonus(){
		return (this.verificado? 15 : 0);
	}
}
