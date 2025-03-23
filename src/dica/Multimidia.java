package dica;

import middleware.Validator;

/**
 * A classe {@code Multimidia} representa um anexo do tipo multimídia (áudio ou vídeo) que pode ser adicionado a uma dica.
 * Ela implementa a interface {@code IAnexo} e fornece métodos para gerar resumos, detalhes e calcular o bônus associado ao anexo.
 */
public class Multimidia implements IAnexo {
	private String link, cabecalho;
	private int duracao;

	public Multimidia(String link, String cabecalho, int duracao) {
		Validator.verifyStringBlank(link, "LINK");
		Validator.verifyStringBlank(cabecalho, "CABECALHO");

		this.link = link;
		this.cabecalho = cabecalho;
		this.duracao = duracao;
	}

	@Override
	public String getResumo() {
		return this.link + "\n" + this.cabecalho;
	}

	@Override
	public String getDetalhado() {
		return this.link + "\n" + this.duracao + "s\n" + this.cabecalho;
	}

	@Override
	public double calcularBonus() {
		return (this.duracao / 60) * 5;
	}
}
