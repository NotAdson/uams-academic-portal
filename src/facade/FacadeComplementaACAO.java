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
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorDica.adicionarDica(cpf, tema, controladorUsuario);
	}
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorDica.adicionarElemetoTextoDica(cpf, posicao, texto, controladorUsuario);
	}
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorDica.adicionarElementoMultimidiaDica(cpf, posicao, link, cabecalho, tempo, controladorUsuario);
	}
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorDica.adicionarElementoReferenciaDica(cpf, posicao, titulo, fonte, ano, conferida, importancia, controladorUsuario);
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
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.criarAtividadeMonitoria(cpf, unidadeAcumulada, disciplina);
	}
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.alterarDescricaoAtividade(cpf, codigoAtividade, descricao);
	}
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.alterarComprovacaoAtividade(cpf, codigoAtividade, linkComprovacao);
	}
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.criarAtividadePesquisaExtensao(cpf, unidadeAcumulada, subtipo);
	}
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, int unidadeAcumulada, String nomeEmpresa) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.criarAtividadeEstagio(cpf, unidadeAcumulada, nomeEmpresa);
	}
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.criarAtividadeRepresentacao(cpf, unidadeAcumulada, subtipo);
	}
	public int creditosAtividade(String cpf, String senha, String tipo) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.getCreditoAtividade(cpf, tipo);
	}
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.gerarMapaCreditos(cpf);
	}
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorAtividade.isMetaAlcancada(cpf);
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.gerarRelatorioFinal(cpf, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.gerarRelatorioFinalPorAtividade(cpf, tipoAtividade, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.gerarRelatorioParcial(cpf, salvar, controladorUsuario, controladorAtividade);
	}
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.gerarRelatorioParcialPorAtividade(cpf, salvar, tipoAtividade, controladorUsuario, controladorAtividade);
	}
	public String listarHistorico(String cpf, String senha) {
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.listarHistorico(cpf);
	}
	public boolean excluirItemHistorico(String cpf, String senha, String data) {	
		controladorUsuario.autenticarUsuario(cpf, senha);
		return controladorRelatorio.excluirItemHistorico(cpf, data);
	}
}


