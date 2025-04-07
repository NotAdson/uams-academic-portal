package relatorio;

import java.util.HashMap;

import usuario.UsuarioController;
import atividade.AtividadeController;
import java.time.LocalDate;

/**
 * Controlador responsável pela geração e gestão de relatórios acadêmicos.
 * 
 * Oferece funcionalidades para criação de relatórios finais e parciais,
 * com capacidade de armazenamento histórico e recuperação de relatórios
 * anteriores organizados por data.
 */
public class RelatorioController {

	HashMap<String, HashMap<String, String>> relatorios;

	public RelatorioController(){
		this.relatorios = new HashMap<>();
	}

	/**
	 * Gera relatório consolidado final com todas as atividades do estudante.
	 * 
	 * @param cpf Identificação do estudante
	 * @param uc Controlador de usuários para obtenção de dados
	 * @param ac Controlador de atividades para cálculo de créditos
	 * @return Relatório formatado com informações completas ou mensagem de erro caso não tenha batido a meta
	 */
	public String gerarRelatorioFinal(String cpf, UsuarioController uc, AtividadeController ac){
		StringBuilder relatorio = new StringBuilder();
		
		if(!ac.isMetaAlcancada(cpf)){
			return "META NÃO ALCANÇADA";
		}

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaCreditos(cpf)).append("\n");
		relatorio.append("TOTAL: ").append(ac.getTotalCreditos(cpf));

		return relatorio.toString();	
	}

	
	/**
	 * Gera relatório específico para um tipo de atividade.
	 * 
	 * @param cpf Identificação do estudante
	 * @param tipo Tipo de atividade a ser relatada
	 * @param uc Controlador de usuários para obtenção de dados
	 * @param ac Controlador de atividades para cálculo de créditos
	 * @return Relatório formatado com informações da atividade específica ou mensagem de erro caso não tenha batido a meta
	 */
	public String gerarRelatorioFinalPorAtividade(String cpf, String tipo, UsuarioController uc, AtividadeController ac){
		StringBuilder relatorio = new StringBuilder();

		if(!ac.isMetaAlcancada(cpf)){
			return "META NÃO ALCANÇADA";
		}

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.getCreditoAtividade(cpf, tipo)).append("\n");

		return relatorio.toString();
	}

	/**
	 * Gera relatório parcial com opção de armazenamento no histórico.
	 * 
	 * @param cpf Identificação do estudante
	 * @param salvar Indica se o relatório deve ser armazenado
	 * @param uc Controlador de usuários para obtenção de dados
	 * @param ac Controlador de atividades para cálculo de créditos
	 * @return Relatório formatado com informações atuais
	 */
	public String gerarRelatorioParcial(String cpf, boolean salvar, UsuarioController uc, AtividadeController ac){
		StringBuilder relatorio = new StringBuilder();

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaCreditos(cpf)).append("\n");
	
		String result = relatorio.toString();

		if(!salvar){
			return result;
		}

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}
	
	/**
	 * Gera relatório parcial específico por tipo de atividade.
	 * 
	 * @param cpf Identificação do estudante
	 * @param salvar Indica se o relatório deve ser armazenado
	 * @param tipo Tipo de atividade a ser relatada
	 * @param uc Controlador de usuários para obtenção de dados
	 * @param ac Controlador de atividades para cálculo de créditos
	 * @return Relatório formatado com informações da atividade específica
	 */
	public String gerarRelatorioParcialPorAtividade(String cpf, boolean salvar, String tipo, UsuarioController uc, AtividadeController ac){
		StringBuilder relatorio = new StringBuilder();

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaAtividade(cpf, tipo)).append("\n");
	
		String result = relatorio.toString();

		if(!salvar){
			return result;
		}

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}
	
	/**
	 * Remove um relatório específico do histórico.
	 * 
	 * @param cpf Identificação do estudante
	 * @param data Data do relatório no formato ISO (YYYY-MM-DD)
	 * @return true se a remoção foi bem-sucedida, false caso contrário
	 */
	public boolean excluirItemHistorico(String cpf, String data){
		if(!this.relatorios.containsKey(cpf) || !this.relatorios.get(cpf).containsKey(data)){
			return false;
		}

		this.relatorios.get(cpf).remove(data);
		return true;
	}

	/**
	 * Lista todo o histórico de relatórios armazenados para um estudante.
	 * 
	 * @param cpf Identificação do estudante
	 * @return Concatenção de todos os relatórios armazenados ou string vazia se não houver histórico
	 */
	public String listarHistorico(String cpf){
		if(!this.relatorios.containsKey(cpf)){
			return "";
		}

		StringBuilder result = new StringBuilder();

		for(String relatorio: this.relatorios.get(cpf).values()){
			result.append(relatorio).append("\n");
		}

		return result.toString();
	}
}
