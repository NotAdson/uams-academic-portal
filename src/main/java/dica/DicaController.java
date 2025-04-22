package dica;

import java.util.ArrayList;

import middleware.Validator;
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
	 * Cria e registra uma nova dica no sistema.
	 * 
	 * @param cpf Identificação do usuário criador
	 * @param tema Tema principal da dica (não pode ser vazio)
	 * @param uc Controlador de usuários para validação
	 * @return Índice da nova dica na lista interna
	 * @throws IllegalArgumentException Se o tema for inválido
	 */
	public int adicionarDica(String cpf, String tema, UsuarioController uc) {
		Validator.verifyStringBlank(tema, "TEMA");

		Dica dica = new Dica(uc.getNomeUsuario(cpf), tema);
		dicas.add(dica);
		return dicas.size() - 1;
	}

	/**
	 * Adiciona conteúdo textual a uma dica existente.
	 * 
	 * @param cpf Identificação do usuário
	 * @param posicao Índice da dica alvo
	 * @param texto Conteúdo a ser adicionado (máximo 500 caracteres)
	 * @param uc Controlador de usuários para registro de bônus
	 * @return Status da operação (true = sucesso, false = texto muito longo ou posição não existe)
	 */
	public boolean adicionarElemetoTextoDica(String cpf, int posicao, String texto, UsuarioController uc) {	
		Validator.verifyStringBlank(texto, "TEXTO");

		if(texto.length() > 500 || 0 > posicao || posicao >= this.dicas.size()){
			return false;
		}

		uc.adicionarBonus(cpf, this.dicas.get(posicao).adicionarElementoTexto(texto));

		return true;
	}

	/**
	 * Adiciona conteúdo multimídia a uma dica existente.
	 * 
	 * @param cpf Identificação do usuário
	 * @param posicao Índice da dica alvo
	 * @param link URL do conteúdo
	 * @param cabecalho Descrição do conteúdo
	 * @param tempo Duração em segundos
	 * @param uc Controlador de usuários para registro de bônus
	 * @return Status da operação (true = sucesso, false = posição inválida)
	 */
	public boolean adicionarElementoMultimidiaDica(String cpf, int posicao, String link, String cabecalho, int tempo, UsuarioController uc) {
		Validator.verifyStringBlank(link, "LINK");
		Validator.verifyStringBlank(cabecalho, "CABEÇALHO");
		
		if(0 > posicao || posicao >= this.dicas.size()) return false;

		uc.adicionarBonus(cpf, this.dicas.get(posicao).adicionarElementoMultimidia(link, cabecalho, tempo));
		return true;
	}


	/**
	 * Adiciona referência bibliográfica a uma dica existente.
	 * 
	 * @param cpf Identificação do usuário
	 * @param posicao Índice da dica alvo
	 * @param titulo Título da referência
	 * @param fonte Origem da referência
	 * @param ano Ano de publicação
	 * @param conferida Status de verificação
	 * @param importancia Nível de relevância (1-5)
	 * @param uc Controlador de usuários para registro de bônus
	 * @return Status sempre verdadeiro quando válido
	 */
	public boolean adicionarElementoReferenciaDica(String cpf, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia, UsuarioController uc) {
		Validator.verifyStringBlank(titulo, "TITULO");
		Validator.verifyStringBlank(fonte, "FONTE");
		
		if(0 > posicao || posicao >= this.dicas.size()) return false;

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
