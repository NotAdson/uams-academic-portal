package relatorio;

import java.util.HashMap;

import usuario.UsuarioController;
import atividade.AtividadeController;
import java.time.LocalDate;

public class RelatorioController {

	HashMap<String, HashMap<String, String>> relatorios;

	public RelatorioController(){
		this.relatorios = new HashMap<>();
	}

	public String gerarRelatorioFinal(String cpf, String senha, UsuarioController uc, AtividadeController ac){
		uc.autenticarUsuario(cpf, senha);

		StringBuilder relatorio = new StringBuilder();
		
		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaCreditos(cpf, senha, uc)).append("\n");
		relatorio.append("TOTAL: ").append(ac.getTotalCreditos(cpf));

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		String result = relatorio.toString();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}

	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipo, UsuarioController uc, AtividadeController ac){
		uc.autenticarUsuario(cpf, senha);

		StringBuilder relatorio = new StringBuilder();
		
		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.getCreditoAtividade(cpf, senha, tipo, uc)).append("\n");

		if(!this.relatorios.containsKey(cpf)){
			this.relatorios.put(cpf, new HashMap<>());
		}
		
		LocalDate data = LocalDate.now();
		String result = relatorio.toString();
		this.relatorios.get(cpf).put(data.toString(), result);

		return result;
	}

	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar, UsuarioController uc, AtividadeController ac){
		uc.autenticarUsuario(cpf, senha);

		StringBuilder relatorio = new StringBuilder();

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaCreditos(cpf, senha, uc)).append("\n");
	
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
	
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipo, UsuarioController uc, AtividadeController ac){
		uc.autenticarUsuario(cpf, senha);

		StringBuilder relatorio = new StringBuilder();

		relatorio.append(uc.getUsuario(cpf)).append("\n");
		relatorio.append(ac.gerarMapaAtividade(cpf, senha, tipo, uc)).append("\n");
	
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

	public boolean excluirItemHistorico(String cpf, String senha, String data, UsuarioController uc){
		uc.autenticarUsuario(cpf, senha);

		if(!this.relatorios.containsKey(cpf) || !this.relatorios.get(cpf).containsKey(data)){
			return false;
		}

		this.relatorios.get(cpf).remove(data);
		return true;
	}


	public String listarHistorico(String cpf, String senha, UsuarioController uc){
		uc.autenticarUsuario(cpf, senha);

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
