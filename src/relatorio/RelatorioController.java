package relatorio;

import java.util.HashMap;
import java.time.LocalDate;

public class RelatorioController {

	HashMap<String, HashMap<String, String>> relatorios;

	public RelatorioController(){
		this.relatorios = new HashMap<>();
	}

	public String gerarRelatorioFinal(String cpf, String usuario, String atividades, int totalCreditos){
		StringBuilder relatorio = new StringBuilder();
		
		relatorio.append(usuario).append("\n");
		relatorio.append(atividades).append("\n");
		relatorio.append("TOTAL: ").append(totalCreditos);

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		String result = relatorio.toString();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}

	public String gerarRelatorioFinalPorAtividade(String cpf, String usuario, String atividade){
		StringBuilder relatorio = new StringBuilder();
		
		relatorio.append(usuario).append("\n");
		relatorio.append(atividade).append("\n");

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		String result = relatorio.toString();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}

	public String gerarRelatorioParcial(String cpf, String usuario, boolean salvar, String atividade){
		StringBuilder relatorio = new StringBuilder();

		relatorio.append(usuario).append("\n");
		relatorio.append(atividade).append("\n");
	
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

	public boolean excluirItemHistorico(String cpf, String data){
		if(!this.relatorios.containsKey(cpf) || !this.relatorios.get(cpf).containsKey(data)){
			return false;
		}

		this.relatorios.get(cpf).remove(data);
		return true;
	}


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
