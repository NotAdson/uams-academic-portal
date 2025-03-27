package dica;

import java.util.ArrayList;

import middleware.Validator;

/**
 * A classe Dica representa uma dica que pode conter vários anexos, como textos, multimídias e referências.
 * Cada dica possui um nome, um tema e uma lista de anexos que podem ser adicionados para enriquecer o conteúdo da dica.
 * A classe fornece métodos para adicionar diferentes tipos de anexos e gerar resumos e detalhes da dica.
 */
public class Dica {
	private String nome, tema;
	private ArrayList<IAnexo> anexos;

	/**
	 * Construtor da classe Dica.
	 *
	 * @param nome  O nome da dica.
	 * @param tema  O tema da dica.
	 * @throws NullPointerException Quando nome ou tema são nulos
	 * @throws IllegalArgumentException Quando nome ou tema são vazios
	 */
	public Dica(String nome, String tema) {
		Validator.verifyStringBlank(nome, "NOME");
		Validator.verifyStringBlank(tema, "TEMA");

		this.nome = nome;
		this.tema = tema;
		this.anexos = new ArrayList<>();
	}

	/**
	 * Gera um resumo da dica, incluindo o nome da dica e os resumos de todos os anexos.
	 *
	 * @return Uma string contendo o resumo da dica.
	 */
	public String getResumo() {
		StringBuilder resumo = new StringBuilder();
		resumo.append(this.nome).append("\n");

		for (IAnexo anexo : this.anexos) {
			resumo.append(anexo.getResumo());
		}

		return resumo.toString();
	}

	/**
	 * Gera uma versão detalhada da dica, incluindo o nome da dica e os detalhes de todos os anexos.
	 *
	 * @return Uma string contendo os detalhes da dica.
	 */
	public String getDetalhado() {
		StringBuilder detalhado = new StringBuilder();
		detalhado.append(this.nome).append("\n");

		for (IAnexo anexo : this.anexos) {
			detalhado.append(anexo.getDetalhado());
		}

		return detalhado.toString();
	}

	/**
	 * Adiciona um anexo do tipo multimídia à dica.
	 *
	 * @param link       O link para o conteúdo multimídia.
	 * @param cabecalho  O cabeçalho descritivo do conteúdo multimídia.
	 * @param duracao    A duração do conteúdo multimídia em segundos.
	 * @return O valor do bônus calculado para o anexo multimídia adicionado.
	 */
	public int adicionarElementoMultimidia(String link, String cabecalho, int duracao) {
		IAnexo anexo = new Multimidia(link, cabecalho, duracao);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}

	/**
	 * Adiciona um anexo do tipo texto à dica.
	 *
	 * @param texto  O conteúdo textual do anexo.
	 * @return O valor do bônus calculado para o anexo de texto adicionado.
	 */
	public int adicionarElementoTexto(String texto) {
		IAnexo anexo = new Texto(texto);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}

	/**
	 * Adiciona um anexo do tipo referência à dica.
	 *
	 * @param titulo      O título da referência.
	 * @param fonte       A fonte da referência.
	 * @param verificado  Indica se a referência foi verificada.
	 * @param ano         O ano de publicação da referência.
	 * @param relevancia  A relevância da referência (1 a 5).
	 * @return O valor do bônus calculado para o anexo de referência adicionado.
	 */
	public int adicionarElementoReferencia(String titulo, String fonte, boolean verificado, int ano, int relevancia) {
		IAnexo anexo = new Referencia(titulo, fonte, verificado, ano, relevancia);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}
}
