package atividade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;

import middleware.Validator;

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

		HashMap<String, Integer> maximos = new HashMap<>();
		
		maximos.put("PESQUISA_EXTENSAO", MAX_CREDITOS_EXTENSAO);
		maximos.put("MONITORIA", MAX_CREDITOS_MONITORIA);
		maximos.put("ESTAGIO", MAX_CREDITOS_ESTAGIO);
		maximos.put("REPRESENTACAO_ESTUDANTIL", MAX_CREDITOS_REPRESENTACAO_ESTUDANTIL);

	}

	public boolean alterarDescricaoAtividade(String codigo, String descricao){
		Map.Entry<String, Integer> elemento;
		String cpf;
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

	public boolean alterarComprovacaoAtividade(String codigo, String link){
		Map.Entry<String, Integer> elemento;
		String cpf;
		int position;

		try {
			elemento = tratarCodigo(codigo);
		} catch (IllegalArgumentException e) {
			return false;
		}

		cpf = elemento.getKey(); position = elemento.getValue();
		
		if(this.atividades.containsKey(cpf) && (0 < position || position > this.atividades.get(cpf).size())) return false;

		this.atividades.get(cpf).get(position).setLink(link);
		return true;
	}

	public String criarAtividadePesquisaExtensao(String cpf, int unidadeAcumulado, String subtipo){
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		String codigo = cpf + "_" + this.atividades.get(cpf).size() + 1;
		Atividade extensao = new Extensao(codigo, unidadeAcumulado, subtipo);
		
		this.atividades.get(cpf).add(extensao);
		return codigo;
	}

	public String criarAtividadeEstagio(String cpf, int unidadeAcumulado, String empresa){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade estagio = new Estagio(codigo, unidadeAcumulado, empresa);
		
		if(unidadeAcumulado < MIN_HORAS_ESTAGIO) return "ABAIXO DO MINIMO DE HORAS";
	
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(estagio);
		return codigo;
	}

	public String criarAtividadeRepresentacao(String cpf, int unidadeAcumulado, String subtipo){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade representacao = new Monitoria(codigo, unidadeAcumulado, subtipo);
		
		if(unidadeAcumulado < MIN_ANOS_REPRESENTACAO_ESTUDANTIL) return "ABAIXO DO MINIMO DE ANOS";
		
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(representacao);
		return codigo;
	}

	public String criarAtividadeMonitoria(String cpf, int unidadeAcumulado, String disciplina){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade monitoria = new Monitoria(codigo, unidadeAcumulado, disciplina);
		
		if(unidadeAcumulado < MIN_SEMESTRE_MONITORIA) return "ABAIXO DO MINIMO DE SEMESTRES";
		
		if(!this.atividades.containsKey(cpf)) this.atividades.put(cpf, new ArrayList<>());

		this.atividades.get(cpf).add(monitoria);
		return codigo;
	}

	public int getCreditoAtividade(String cpf, String tipo){
		int totalCreditos = 0;

		for(Atividade atividade: this.atividades.get(cpf)){
			if(atividade.getTipo().equals(tipo)) totalCreditos += atividade.getCreditos();
		}

		return Math.min(this.maximos.get(tipo), totalCreditos);
	}

	public String gerarMapaAtividade(String cpf, String tipo){
		StringBuilder result = new StringBuilder();

		result.append(this.getCreditoAtividade(cpf, tipo));

		if(this.maximos.containsKey(tipo)) result.append("/").append(this.maximos.get(tipo));

		return result.toString();
	}

	public String gerarMapaCreditos(String cpf){
		StringBuilder result = new StringBuilder();
		for(String tipo: this.maximos.keySet()){
			result.append(this.gerarMapaAtividade(cpf, tipo));
		}

		return result.toString();
	}

	public int getTotalCreditos(String cpf){
		int total = 0;
		
		for(String tipo: this.maximos.keySet()){
			total += this.getCreditoAtividade(cpf, tipo);
		}

		return total;
	}

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
}
