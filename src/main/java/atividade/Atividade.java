package atividade;

import middleware.Validator;

/**
 * A classe {@code Atividade} representa uma atividade que pode ser realizada por um estudante.
 */
public abstract class Atividade {
	private int tempo;
	private double creditos;
	private String descricao, link, codigo, tipo;

	public Atividade(String codigo, String tipo, double creditos, int tempo){
		Validator.verifyStringBlank(codigo, "CODIGO");
		this.codigo = codigo;
		this.descricao = "";
		this.link = "";
		this.tipo = tipo;
		this.creditos = creditos;
		this.tempo = tempo;
	}

	public String getCodigo(){
		return this.codigo;
	}

	public String getDescricao(){
		return this.descricao;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public String getLink(){
		return this.link;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getTipo(){
		return this.tipo;
	}

	/**
	 * Calcula e retorna o total de créditos obtidos na atividade.
	 * 
	 * O cálculo é realizado multiplicando os créditos base pelo tempo investido
	 * na atividade, retornando o valor como inteiro.
	 * 
	 * @return Total de créditos calculados (créditos base × tempo)
	 */
	public int getCreditos(){
		return (int)(this.creditos * this.tempo);
	}
}
