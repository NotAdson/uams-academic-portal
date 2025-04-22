package usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import middleware.Validator;

/**
 * Controlador central para gestão de usuários do sistema acadêmico.
 * 
 * Responsável por operações críticas como autenticação, cadastro,
 * modificação de senhas e gerenciamento de bônus dos usuários.
 */
public class UsuarioController {
	private HashMap<String, Usuario> usuarios;

	public UsuarioController(){
		this.usuarios = new HashMap<>();
	}

	/**
	 * Valida as credenciais de acesso de um usuário.
	 * 
	 * @param cpf Número do CPF para autenticação
	 * @param senha Senha do usuário
	 * @throws IllegalArgumentException Se as credenciais forem inválidas
	 */
	public void autenticarUsuario(String cpf, String senha){
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(senha, "SENHA");

		if(!this.usuarios.containsKey(cpf) || !this.usuarios.get(cpf).verificaSenha(senha)){
			throw new IllegalArgumentException("CPF OU SENHA INCORRETOS!");
		}
	}

	/**
	 * Registra um novo usuário no sistema.
	 * 
	 * @param nome Nome completo do usuário
	 * @param cpf CPF no formato 000.000.000-00
	 * @param senha Senha com exatamente 8 caracteres
	 * @param matricula Número de matrícula institucional
	 * @return true se cadastro foi bem-sucedido, false caso contrário
	 */
	public boolean criarUsuario(String nome, String cpf, String senha, String matricula){
		Validator.verifyStringBlank(nome, "NOME");
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(senha, "SENHA");
		Validator.verifyStringBlank(matricula, "MATRICULA");

		if(usuarios.containsKey(cpf) || senha.length() != 8 || cpf.length() != 14 || Validator.countDigit(cpf) != 11){
			return false;
		}

		Usuario novo;

		try {
			novo = new Usuario(cpf, senha, nome, matricula);
		} catch (Exception e) {
			return false;
		}

		this.usuarios.put(cpf, novo);

		return true;
	}

	/**
	 * Altera a senha de um usuário existente.
	 * 
	 * @param cpf CPF do usuário
	 * @param senha Senha atual
	 * @param novaSenha Nova senha (deve seguir as mesmas regras de cadastro)
	 * @return true se a alteração foi bem-sucedida
	 * @throws IllegalArgumentException Se a autenticação falhar
	 */
	public boolean alterarSenhaUsuario(String cpf, String senha, String novaSenha){
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(senha, "SENHA");
		Validator.verifyStringBlank(novaSenha, "NOVA SENHA");

		this.autenticarUsuario(cpf, senha);
		this.usuarios.get(cpf).setSenha(novaSenha);
		return true;
	}

	public String getNomeUsuario(String cpf){
		Validator.verifyStringBlank(cpf, "CPF");
		return this.usuarios.get(cpf).getNome();
	}

	/**
	 * Adiciona pontos de bônus ao perfil do usuário.
	 * 
	 * @param cpf CPF do usuário beneficiado
	 * @param bonus Quantidade de pontos a adicionar
	 */
	public void adicionarBonus(String cpf, int bonus){
		Validator.verifyStringBlank(cpf, "CPF");
		Usuario atual = this.usuarios.get(cpf);

		atual.setBonus(atual.getBonus() + bonus);
	}

	public String getUsuario(String cpf){
		Validator.verifyStringBlank(cpf, "CPF");
		if(!this.usuarios.containsKey(cpf)){
			return "USUÁRIO NÃO EXISTE";
		}

		return this.usuarios.get(cpf).toString();
	}
	
	
	/**
	 * Gera lista ordenada alfabeticamente de todos os usuários cadastrados.
	 * 
	 * @return Array com representações textuais dos usuários
	 */
	public String[] exibirUsuarios(){
		final int SIZE = this.usuarios.size();
		String[] result = new String[SIZE];
		ArrayList<Usuario> cadastrados = new ArrayList<>();

		for(Usuario atual: this.usuarios.values()){
			cadastrados.add(atual);
		}

		Collections.sort(cadastrados);

		for(int i = 0; i < SIZE; i++){
			result[i] = cadastrados.get(i).toString();
		}

		return result;
	}

	/**
	 * Gera ranking de usuários ordenado por pontuação de bônus.
	 * 
	 * @return Array com representações textuais dos usuários em ordem decrescente de bônus
	 */
	public String[] listarRankingBonus(){
		final int SIZE = this.usuarios.size();
		String[] result = new String[SIZE];
		ArrayList<Usuario> cadastrados = new ArrayList<>();

		for(Usuario atual: this.usuarios.values()){
			cadastrados.add(atual);
		}
		
		UsuarioBonusComparator comparador = new UsuarioBonusComparator();
		Collections.sort(cadastrados, comparador);

		for(int i = 0; i < SIZE; i++){
			result[i] = cadastrados.get(i).toString();
		}

		return result;
	}
}
