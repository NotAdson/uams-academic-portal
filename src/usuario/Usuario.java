package usuario;

import java.util.Objects;
import middleware.Validator;

/**
 * Representa um usuário do sistema acadêmico com informações pessoais e credenciais.
 * 
 * A classe implementa comparação natural por nome e mantém um sistema
 * de pontuação por bônus para atividades acadêmicas.
 */
public class Usuario implements Comparable<Usuario> {

	private String cpf, matricula, senha, nome;
	private int quantidadeBonus;

	public Usuario(String cpf, String senha, String nome, String matricula) {
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(nome, "NOME");
		Validator.verifyStringBlank(matricula, "MATRICULA");

		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		Validator.verifyStringBlank(nome, "NOME");
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public int getBonus() {
		return this.quantidadeBonus;
	}

	public void setBonus(int bonus) {
		this.quantidadeBonus = bonus;
	}

	/**
	 * Verifica se a senha fornecida corresponde à senha do usuário.
	 * 
	 * @param senha Senha a ser verificada
	 * @return true se a senha for correta, false caso contrário
	 * @throws IllegalArgumentException Se a senha for vazia ou nula
	 */
	public boolean verificaSenha(String senha) {
		Validator.verifyStringBlank(senha, "SENHA");
		return this.senha.equals(senha);
	}

	public void setSenha(String senha) {
		Validator.verifyStringBlank(senha, "SENHA");
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}

	/**
	 * Comparação natural entre usuários baseada no nome.
	 * 
	 * @param other Outro usuário a ser comparado
	 * @return Valor negativo, zero ou positivo conforme a ordem alfabética
	 */
	@Override
	public int compareTo(Usuario other) {
		return this.nome.compareTo(other.nome);
	}

	/**
	 * Representação textual padrão do usuário.
	 * 
	 * @return String formatada com nome, CPF e matrícula
	 */
	@Override
	public String toString() {
		return this.nome + ", " + this.cpf + ", " + this.matricula;
	}
}
