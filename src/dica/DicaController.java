package dica;

import java.util.ArrayList;

import usuario.UsuarioController;

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
	public int adicionarDica(String cpf, String senha, String tema, UsuarioController uc) {
		uc.autenticarUsuario(cpf, senha);
		Dica dica = new Dica(uc.getNomeUsuario(cpf), tema);
		dicas.add(dica);
		return dicas.size() - 1;
	}

	/**
	 * Adiciona um elemento do tipo texto a uma dica existente.
	 *
	 * @param posicao A posição da dica na lista.
	 * @param texto   O conteúdo textual a ser adicionado.
	 * @return O valor do bônus calculado para o elemento de texto adicionado ou -1 caso não seja possível adicionar o texto.
	 * @throws IndexOutOfBoundsException Se a posição for fora dos limites da lista.
	 */
	public boolean adicionarElemetoTextoDica(String cpf, String senha, int posicao, String texto, UsuarioController uc) {	
		uc.autenticarUsuario(cpf, senha);
		if(texto.length() > 500){
			return false;
		}

		uc.adicionarBonus(cpf, this.dicas.get(posicao).adicionarElementoTexto(texto));

		return true;
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
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo, UsuarioController uc) {
		uc.autenticarUsuario(cpf, senha);
		uc.adicionarBonus(cpf, this.dicas.get(posicao).adicionarElementoMultimidia(link, cabecalho, tempo));
		return true;
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
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia, UsuarioController uc) {
		uc.autenticarUsuario(cpf, senha);
		uc.adicionarBonus(cpf, this.dicas.get(posicao).adicionarElementoReferencia(titulo, fonte, conferida, ano, importancia));

		return true;
	}

	/**
	 * Lista as dicas(versão resumida)
	 *
	 * @return A representação da dica em formato resumido.
	 */
	public String[] listarDicas(){
		String[] result = new String[this.dicas.size()];
		
		for(int i = 0; i < this.dicas.size(); i++){
			result[i] = this.dicas.get(i).getResumo();
		}

		return result;
	}

	/**
	 * Lista as dicas(versão detalhada)
	 *
	 * @return Um array com a representação das dicas em formato detalhado.
	 */
	public String[] listarDicasDetalhes(){
		String[] result = new String[this.dicas.size()];
		
		for(int i = 0; i < this.dicas.size(); i++){
			result[i] = this.dicas.get(i).getDetalhado();
		}

		return result;
	}
	
	/**
	 * Pega o resumo de uma dica pela posição
	 *
	 * @param posicao posição da dica
	 * @return resumo da dica
	 * */
	public String listarDica(int posicao){
		return this.dicas.get(posicao).getResumo();
	}

	/**
	 * Pega os detalhes de uma dica pela posição
	 *
	 * @param posicao posição da dica
	 * @return detalhes da dica
	 * */
	public String listarDicaDetalhes(int posicao){
		return this.dicas.get(posicao).getDetalhado();
	}
}
