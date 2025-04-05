package usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import middleware.Validator;

public class UsuarioController {
	/*
	 * Falta validações de argumento
	 * Verificar digitos CPF
	 */
	private HashMap<String, Usuario> usuarios;

	public UsuarioController(){
		this.usuarios = new HashMap<>();
	}

	public void autenticarUsuario(String cpf, String senha){
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(senha, "SENHA");

		if(!this.usuarios.containsKey(cpf) || !this.usuarios.get(cpf).verificaSenha(senha)){
			throw new IllegalArgumentException("CPF OU SENHA INCORRETOS!");
		}
	}

	public boolean criarUsuario(String nome, String cpf, String senha, String matricula){
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

	public boolean alterarSenhaUsuario(String cpf, String senha, String novaSenha){
		this.autenticarUsuario(cpf, senha);
		this.usuarios.get(cpf).setSenha(novaSenha);
		return true;
	}

	public String getNomeUsuario(String cpf){
		return this.usuarios.get(cpf).getNome();
	}

	public void adicionarBonus(String cpf, int bonus){
		Usuario atual = this.usuarios.get(cpf);

		atual.setBonus(atual.getBonus() + bonus);
	}

	public String getUsuario(String cpf){
		if(!this.usuarios.containsKey(cpf)){
			return "USUÁRIO NÃO EXISTE";
		}

		return this.usuarios.get(cpf).toString();
	}

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
