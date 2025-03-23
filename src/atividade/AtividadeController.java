package atividade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;

import middleware.Validator;

public class AtividadeController {
	private static int maxCreditosMonitoria = 16, minSemestreMonitoria = 1;
	private static int maxCreditosEstagio = 18, minHorasEstagio = 300;
	private static int maxCreditosRepresentacaoEstudantil = 2, minAnosRepresentacaoEstudantil = 1;
	private static int maxCreditosExtensao = 18;

	private HashMap<String, ArrayList<Atividade>> atividades;
	
	public AtividadeController(){
		this.atividades = new HashMap<String, ArrayList<Atividade>>();
	}


	private static Map.Entry<String, Integer> tratarCodigo(String codigo){
		String[] splited = codigo.split("_");
		
		if(splited.length != 2 || !Validator.isStringDigit(splited[1])){
			throw new IllegalArgumentException("CODIGO INVÁLIDO!");
		}
		
		Integer position = Integer.parseInt(splited[1]);
		Map.Entry<String, Integer> result = new AbstractMap.SimpleEntry<>(splited[0], position);
		return result;
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
		
		if(this.atividades.containsKey(cpf) && (0 < position || position > this.atividades.get(cpf).size())){
			return false;
		}

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
		
		if(this.atividades.containsKey(cpf) && (0 < position || position > this.atividades.get(cpf).size())){
			return false;
		}

		this.atividades.get(cpf).get(position).setLink(link);
		return true;
	}

	public String criarAtividadePesquisaExtensao(String cpf, int unidadeAcumulado, String subtipo, int usuarioCredito){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade extensao = new Extensao(codigo, unidadeAcumulado, subtipo);
		
		if(extensao.getCreditos() + usuarioCredito > maxCreditosExtensao){
			return "LIMITE DE CREDITOS ULTRAPASSADO";
		}
		
		this.atividades.get(cpf).add(extensao);
		return codigo;
	}
	public String criarAtividadeEstagio(String cpf, int unidadeAcumulado, String empresa, int usuarioCredito){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade estagio = new Estagio(codigo, unidadeAcumulado, empresa);
		
		if(unidadeAcumulado < minHorasEstagio){
			return "ABAIXO DO MINIMO DE HORAS";
		}else if(estagio.getCreditos() + usuarioCredito > maxCreditosEstagio){
			return "LIMITE DE CREDITOS ULTRAPASSADO";
		}
		
		this.atividades.get(cpf).add(estagio);
		return codigo;
	}
	public String criarAtividadeRepresentacao(String cpf, int unidadeAcumulado, String subtipo, int usuarioCredito){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade representacao = new Monitoria(codigo, unidadeAcumulado, subtipo);
		
		if(unidadeAcumulado < minAnosRepresentacaoEstudantil){
			return "ABAIXO DO MINIMO DE ANOS";
		}else if(representacao.getCreditos() + usuarioCredito > maxCreditosRepresentacaoEstudantil){
			return "LIMITE DE CREDITOS ULTRAPASSADO";
		}
		
		this.atividades.get(cpf).add(representacao);
		return codigo;
	}
	public String criarAtividadeMonitoria(String cpf, int unidadeAcumulado, String disciplina, int usuarioCredito){
		String codigo = cpf + "_" + this.atividades.get(cpf).size();
		Atividade monitoria = new Monitoria(codigo, unidadeAcumulado, disciplina);
		
		if(unidadeAcumulado < minSemestreMonitoria){
			return "ABAIXO DO MINIMO DE SEMESTRES";
		}else if(monitoria.getCreditos() + usuarioCredito > maxCreditosMonitoria){
			return "LIMITE DE CREDITOS ULTRAPASSADO";
		}
		
		this.atividades.get(cpf).add(monitoria);
		return codigo;
	}
}
