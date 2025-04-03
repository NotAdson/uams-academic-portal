package facade;

import atividade.AtividadeController;
import dica.DicaController;
import relatorio.RelatorioController;
import usuario.UsuarioController;

public class FacadeComplementaACAO {
	private UsuarioController controladorUsuario;
	private AtividadeController controladorAtividade;
	private DicaController controladorDica;
	private RelatorioController controladorRelatorio;

	public FacadeComplementaACAO(){
		this.controladorUsuario = new UsuarioController();
		this.controladorAtividade = new AtividadeController();
		this.controladorDica = new DicaController();
		this.controladorRelatorio = new RelatorioController();
	}

	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return controladorUsuario.criarUsuario(nome, cpf, senha, matricula);
	}
	public String[] exibirEstudantes() {
		return controladorUsuario.exibirUsuarios();
	}
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		return controladorUsuario.alterarSenhaUsuario(cpf, senhaAntiga, novaSenha);
	}
	public int adicionarDica(String cpf, String senha, String tema) {
		return controladorDica.adicionarDica(cpf, senha, tema, controladorUsuario);
	}
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		return controladorDica.adicionarElemetoTextoDica(cpf, senha, posicao, texto, controladorUsuario);
	}
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		return controladorDica.adicionarElementoMultimidiaDica(cpf, senha, posicao, link, cabecalho, tempo, controladorUsuario);
	}
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
		return controladorDica.adicionarElementoReferenciaDica(cpf, senha, posicao, titulo, fonte, ano, conferida, importancia, controladorUsuario);
	}

	public String[] listarDicas() {
		return controladorDica.listarDicas();
	}
	public String [] listarDicasDetalhes() {
		return controladorDica.listarDicasDetalhes();
	}
	public String listarDica(int posicao) {
		return controladorDica.listarDica(posicao);
	}
	public String listarDicaDetalhes(int posicao) {
		return controladorDica.listarDicaDetalhes(posicao);
	}
	public String[] listarUsuariosRankingDicas() {
		return controladorUsuario.listarRankingBonus();
	}

	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, int unidadeAcumulada, String disciplina) {
		return controladorAtividade.criarAtividadeMonitoria(cpf, senha, unidadeAcumulada, disciplina, controladorUsuario);
	}
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		return controladorAtividade.alterarDescricaoAtividade(cpf, senha, codigoAtividade, descricao, controladorUsuario);
	}
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		return controladorAtividade.alterarComprovacaoAtividade(cpf, senha, codigoAtividade, linkComprovacao, controladorUsuario);
	}
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return controladorAtividade.criarAtividadePesquisaExtensao(cpf, senha, unidadeAcumulada, subtipo, controladorUsuario);
	}
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, int unidadeAcumulada, String nomeEmpresa) {
		return controladorAtividade.criarAtividadeEstagio(cpf, senha, unidadeAcumulada, nomeEmpresa, controladorUsuario);
	}
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return controladorAtividade.criarAtividadeRepresentacao(cpf, senha, unidadeAcumulada, subtipo, controladorUsuario);
	}
	public int creditosAtividade(String cpf, String senha, String tipo) {
		return controladorAtividade.getCreditoAtividade(cpf, senha, tipo, controladorUsuario);
	}
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		return controladorAtividade.gerarMapaCreditos(cpf, senha, controladorUsuario);
	}
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		return controladorAtividade.isMetaAlcancada(cpf, senha, controladorUsuario);
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		return controladorRelatorio.gerarRelatorioFinal(cpf, senha, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		return controladorRelatorio.gerarRelatorioFinalPorAtividade(cpf, senha, tipoAtividade, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		return controladorRelatorio.gerarRelatorioParcial(cpf, senha, salvar, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		return controladorRelatorio.gerarRelatorioParcialPorAtividade(cpf, senha, salvar, tipoAtividade, controladorUsuario, controladorAtividade);
	}
	public String listarHistorico(String cpf, String senha) {
		return controladorRelatorio.listarHistorico(cpf, senha, controladorUsuario);
	}
	public boolean excluirItemHistorico(String cpf, String senha, String data) {	
		return controladorRelatorio.excluirItemHistorico(cpf, senha, data, controladorUsuario);
	}
}


