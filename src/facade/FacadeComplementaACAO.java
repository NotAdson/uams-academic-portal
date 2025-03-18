package facade;

import java.util.List;

public class FacadeComplementaACAO {
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		//TODO
		return false;
	}
	public String[] exibirEstudantes() {
		//TODO
		return new String[] {};
	}
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		//TODO
		return false;
	}
	public int adicionarDica(String cpf, String senha, String tema) {
		//TODO
		return 0;
	}
	public boolean adicionarElementoTextoDica(String cpf, int senha, int posicao, String pergunta, String resposta) {
		//TODO
		return false;
	}
	public boolean adicionarElementoMultimidiaDica(String cpf, int senha, int posicao, String link, String cabecalho, int tempo) {
		//TODO
		return false;
	}
	public boolean adicionarElementoReferenciaDica(String cpf, int senha, int posicao, List<String> referencias) {
		//TODO
		return false;
	}
	
	public String[] listarDicas() {
		//TODO
		return new String[] {};
	}
	public String [] listarDicasDetalhes() {
		//TODO
		return new String[] {};
	}
	public String listarDica(int posicao) {
		//TODO
		return "";
	}
	public String listarDicaDetalhes(int posicao) {
		//TODO
		return "";
	}
	public String[] listarUsuariosRankingDicas() {
		//TODO
		return new String[] {};
	}

	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String disciplina) {
		//TODO
		return "";
	}
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		//TODO
		return false;
	}
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		//TODO
		return false;
	}
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
		//TODO
		return "";
	}
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String nomeEmpresa) {
		//TODO
		return "";
	}
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		//TODO
		return "";
	}
	public int creditosAtividade(String cpf, String senha, String tipo) {
		//TODO
		return 0;
	}
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		//TODO
		return "";
	}
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		//TODO
		return false;
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		//TODO
		return "";
	}
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		//TODO
		return "";
	}
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		//TODO
		return "";
	}
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		//TODO
		return "";
	}
	public String listarHistorico(String cpf, String senha) {
		//TODO
		return "";
	}
	public boolean excluirItemHistorico(String cpf, String senha, String data) {
		//TODO
		return false;
	}


}
