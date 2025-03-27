package usuario;

public class Usuario implements Comparable<Usuario>{
	
	private String cpf, matricula, senha, nome;
	private int quantidadeBonus;

	public Usuario(String cpf, String senha, String nome, String matricula){
		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getCpf(){
		return this.cpf;
	}

	public String getMatricula(){
		return this.matricula;
	}

	public int getBonus(){
		return this.quantidadeBonus;
	}

	public void setBonus(int bonus){
		this.quantidadeBonus = bonus;
	}

	public boolean verificaSenha(String senha){
		return this.senha.equals(senha);
	}

	public void setSenha(String senha){
		this.senha = senha;
	}

	@Override
	public int compareTo(Usuario other){
		return this.nome.compareTo(other.nome);
	}

	@Override
	public String toString(){
		return this.nome + ", " + this.cpf + ", " + this.matricula;
	}
}
