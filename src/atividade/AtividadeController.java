package atividade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;

import middleware.Validator;

/**
 * Controller responsável por gerenciar atividades acadêmicas de diferentes tipos,
 * incluindo cálculo de créditos, verificação de metas e manipulação de atividades.
 * 
 * Mantém registros de atividades organizados por CPF do estudante e oferece métodos
 * para criação, modificação e consulta de atividades acadêmicas.
 */
public class AtividadeController {
	private final static int MAX_CREDITOS_MONITORIA = 16, MIN_SEMESTRE_MONITORIA = 1;
	private final static int MAX_CREDITOS_ESTAGIO = 18, MIN_HORAS_ESTAGIO = 300;
	private final static int MAX_CREDITOS_REPRESENTACAO_ESTUDANTIL = 2, MIN_ANOS_REPRESENTACAO_ESTUDANTIL = 1;
	private final static int MAX_CREDITOS_EXTENSAO = 18;

	private final static int META_CREDITOS = 22;

	private HashMap<String, Integer> maximos;
	private HashMap<String, ArrayList<Atividade>> atividades;
	
	public AtividadeController(){
		this.atividades = new HashMap<String, ArrayList<Atividade>>();

		maximos = new HashMap<>();
		
		maximos.put("PESQUISA_EXTENSAO", MAX_CREDITOS_EXTENSAO);
		maximos.put("MONITORIA", MAX_CREDITOS_MONITORIA);
		maximos.put("ESTAGIO", MAX_CREDITOS_ESTAGIO);
		maximos.put("REPRESENTACAO_ESTUDANTIL", MAX_CREDITOS_REPRESENTACAO_ESTUDANTIL);

	}

	/**
	 * Altera a descrição de uma atividade existente.
	 * 
	 * @param cpf CPF do estudante dono da atividade
	 * @param codigo Código único da atividade no formato "CPF_INDEX"
	 * @param descricao Nova descrição para a atividade
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alterarDescricaoAtividade(String cpf, String codigo, String descricao){
		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(descricao, "DESCRIÇÃO");

		Map.Entry<String, Integer> elemento;
		int position;

		try {
			elemento = tratarCodigo(codigo);
		} catch (IllegalArgumentException e) {
			return false;
		}

		cpf = elemento.getKey(); position = elemento.getValue();
		
		if(!this.atividades.containsKey(cpf) || (0 < position || position > this.atividades.get(cpf).size())) return false;

		this.atividades.get(cpf).get(position).setDescricao(descricao);
		return true;
	}

	/**
	 * Altera o link de comprovação de uma atividade existente.
	 * 
	 * @param cpf CPF do estudante dono da atividade
	 * @param codigo Código único da atividade no formato "CPF_INDEX"
	 * @param link Novo link de comprovação
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alterarComprovacaoAtividade(String cpf, String codigo, String link){
		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(link, "LINK");

		Map.Entry<String, Integer> elemento;
		int position;

		try {
			elemento = tratarCodigo(codigo);
		} catch (IllegalArgumentException e) {
			return false;
		}

		position = elemento.getValue();
		
		if(this.atividades.containsKey(cpf) && (0 < position || position > this.atividades.get(cpf).size())) return false;

		this.atividades.get(cpf).get(position).setLink(link);
		return true;
	}

	/**
	 * Cria uma nova atividade do tipo Pesquisa e Extensão.
	 * 
	 * @param cpf CPF do estudante
	 * @param unidadeAcumulado Quantidade de unidades acumuladas
	 * @param subtipo Subtipo da atividade de pesquisa/extensão
	 * @return Código da atividade criada ou mensagem de erro
	 */
	public String criarAtividadePesquisaExtensao(String cpf, int unidadeAcumulado, String subtipo){
		Validator.verifyStringBlank(subtipo, "SUBTIPO");
		
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		String codigo = cpf + "_" + this.atividades.get(cpf).size() + 1;
		Atividade extensao = new Extensao(codigo, unidadeAcumulado, subtipo);
		
		this.atividades.get(cpf).add(extensao);
		return codigo;
	}

	/**
	 * Cria uma nova atividade do tipo Estágio.
	 * 
	 * @param cpf CPF do estudante
	 * @param unidadeAcumulado Horas acumuladas no estágio
	 * @param empresa Nome da empresa onde o estágio foi realizado
	 * @return Código da atividade criada ou mensagem de erro se horas forem insuficientes
	 */
	public String criarAtividadeEstagio(String cpf, int unidadeAcumulado, String empresa){
		Validator.verifyStringBlank(empresa, "EMPRESA");

		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade estagio = new Estagio(codigo, unidadeAcumulado, empresa);
		
		if(unidadeAcumulado < MIN_HORAS_ESTAGIO) return "ABAIXO DO MINIMO DE HORAS";
	
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(estagio);
		return codigo;
	}

	/**
	 * Cria uma nova atividade do tipo Representação Estudantil.
	 * 
	 * @param cpf CPF do estudante
	 * @param unidadeAcumulado Anos de representação acumulados
	 * @param subtipo Tipo de representação estudantil
	 * @return Código da atividade criada ou mensagem de erro se anos forem insuficientes
	 */
	public String criarAtividadeRepresentacao(String cpf, int unidadeAcumulado, String subtipo){
		Validator.verifyStringBlank(subtipo, "SUBTIPO");

		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade representacao = new RepresentacaoEstudantil(codigo, unidadeAcumulado, subtipo);
		
		if(unidadeAcumulado < MIN_ANOS_REPRESENTACAO_ESTUDANTIL) return "ABAIXO DO MINIMO DE ANOS";
		
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(representacao);
		return codigo;
	}

	/**
	 * Cria uma nova atividade do tipo Monitoria.
	 * 
	 * @param cpf CPF do estudante
	 * @param unidadeAcumulado Semestres de monitoria acumulados
	 * @param disciplina Nome da disciplina associada à monitoria
	 * @return Código da atividade criada ou mensagem de erro se semestres forem insuficientes
	 */
	public String criarAtividadeMonitoria(String cpf, int unidadeAcumulado, String disciplina){
		Validator.verifyStringBlank(disciplina, "DISCIPLINA");

		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade monitoria = new Monitoria(codigo, unidadeAcumulado, disciplina);
		
		if(unidadeAcumulado < MIN_SEMESTRE_MONITORIA) return "ABAIXO DO MINIMO DE SEMESTRES";
		
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(monitoria);
		return codigo;
	}

	/**
	 * Obtém o total de créditos acumulados para um tipo específico de atividade.
	 * 
	 * @param cpf CPF do estudante
	 * @param tipo Tipo de atividade (PESQUISA_EXTENSAO, MONITORIA, ESTAGIO, etc.)
	 * @return Total de créditos acumulados para o tipo especificado
	 */
	public int getCreditoAtividade(String cpf, String tipo){
		Validator.verifyStringBlank(tipo, "TIPO");

		return this.calcularCreditoAtividade(cpf, tipo);
	}
	
	/**
	 * Gera um mapa de créditos para um tipo específico de atividade no formato "X/Y",
	 * onde X são créditos acumulados e Y é o limite máximo para o tipo.
	 * 
	 * @param cpf CPF do estudante
	 * @param tipo Tipo de atividade
	 * @return String formatada com o mapa de créditos
	 */
	public String gerarMapaAtividade(String cpf, String tipo){
		Validator.verifyStringBlank(tipo, "TIPO");

		return this.calcularMapaAtividade(cpf, tipo);
	}

	/**
	 * Gera um relatório completo com o mapa de créditos para todos os tipos de atividades.
	 * 
	 * @param cpf CPF do estudante
	 * @return String formatada com o mapa de créditos para todas as atividades
	 */
	public String gerarMapaCreditos(String cpf){
		StringBuilder result = new StringBuilder();
		for(String tipo: this.maximos.keySet()){
			result.append(tipo).append(": ").append(this.calcularMapaAtividade(cpf, tipo)).append("\n");
		}

		return result.toString();
	}
	
	/**
	 * Calcula o total de créditos acumulados em todas as atividades.
	 * 
	 * @param cpf CPF do estudante
	 * @return Total de créditos acumulados (limitado a 22)
	 */
	public int getTotalCreditos(String cpf){
		Validator.verifyStringBlank(cpf, "CPF");
		int total = 0;
		
		for(String tipo: this.maximos.keySet()){
			total += this.calcularCreditoAtividade(cpf, tipo);
		}

		return Math.min(22, total);
	}

	/**
	 * Verifica se o estudante atingiu a meta mínima de créditos (22).
	 * 
	 * @param cpf CPF do estudante
	 * @return true se a meta foi atingida, false caso contrário
	 */
	public boolean isMetaAlcancada(String cpf){
		return this.getTotalCreditos(cpf) >= META_CREDITOS;
	}

	private static Map.Entry<String, Integer> tratarCodigo(String codigo){
		String[] splited = codigo.split("_");
		
		if(splited.length != 2 || !Validator.isStringDigit(splited[1])) throw new IllegalArgumentException("CODIGO INVÁLIDO!");
		
		Integer position = Integer.parseInt(splited[1]);
		Map.Entry<String, Integer> result = new AbstractMap.SimpleEntry<>(splited[0], position);
		return result;
	}

	private int calcularCreditoAtividade(String cpf, String tipo){
		Validator.verifyStringBlank(cpf, "CPF");
		Validator.verifyStringBlank(tipo, "TIPO");
		
		if(!this.atividades.containsKey(cpf)){
			return 0;
		}

		int totalCreditos = 0;

		for(Atividade atividade: this.atividades.get(cpf)){
			if(atividade.getTipo().equals(tipo)) totalCreditos += atividade.getCreditos();
		}

		return Math.min(this.maximos.get(tipo), totalCreditos);
	}

	private String calcularMapaAtividade(String cpf, String tipo){
		StringBuilder result = new StringBuilder();

		result.append(this.calcularCreditoAtividade(cpf, tipo));

		if(this.maximos.containsKey(tipo)) result.append("/").append(this.maximos.get(tipo));

		return result.toString();
	}

}
