package dica;

import java.util.ArrayList;

/**
 * A classe {@code DicaController} gerencia a criação e manipulação de dicas e seus anexos.
 * Ela mantém uma lista de dicas e fornece métodos para adicionar novas dicas e elementos (texto, multimídia e referência) a dicas existentes.
 */
public class DicaController {
	private ArrayList<Dica> dicas;

	public DicaController() {
		this.dicas = new ArrayList<>();
	}

	/**
	 * Adiciona uma nova dica à lista de dicas.
	 *
	 * @param nome  O nome da dica.
	 * @param tema  O tema da dica.
	 * @return A posição da dica na lista.
	 */
	public int adicionarDica(String nome, String tema) {
		Dica dica = new Dica(nome, tema);
		dicas.add(dica);
		return dicas.size() - 1;
	}

	/**
	 * Adiciona um elemento do tipo texto a uma dica existente.
	 *
	 * @param posicao A posição da dica na lista.
	 * @param texto   O conteúdo textual a ser adicionado.
	 * @return O valor do bônus calculado para o elemento de texto adicionado.
	 * @throws IndexOutOfBoundsException Se a posição for fora dos limites da lista.
	 */
	public double adicionarElemetoTextoDica(int posicao, String texto) {
		return this.dicas.get(posicao).adicionarElementoTexto(texto);
	}

	/**
	 * Adiciona um elemento do tipo multimídia a uma dica existente.
	 *
	 * @param posicao   A posição da dica na lista.
	 * @param link      O link para o conteúdo multimídia.
	 * @param cabecalho O cabeçalho descritivo do conteúdo multimídia.
	 * @param tempo     A duração do conteúdo multimídia em segundos.
	 * @return O valor do bônus calculado para o elemento de multimídia adicionado.
	 * @throws IndexOutOfBoundsException Se a posição for fora dos limites da lista.
	 */
	public double adicionarElementoMultimidiaDica(int posicao, String link, String cabecalho, int tempo) {
		return this.dicas.get(posicao).adicionarElementoMultimidia(link, cabecalho, tempo);
	}

	/**
	 * Adiciona um elemento do tipo referência a uma dica existente.
	 *
	 * @param posicao    A posição da dica na lista (índice).
	 * @param titulo     O título da referência.
	 * @param fonte      A fonte da referência.
	 * @param ano        O ano de publicação da referência.
	 * @param conferida  Indica se a referência foi verificada.
	 * @param importancia A relevância da referência (1 a 5).
	 * @return O valor do bônus calculado para o elemento de referência adicionado.
	 * @throws IndexOutOfBoundsException Se a posição for inválida (fora dos limites da lista).
	 */
	public double adicionarElementoReferenciaDica(int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
		return this.dicas.get(posicao).adicionarElementoReferencia(titulo, fonte, conferida, ano, importancia);
	}
}
