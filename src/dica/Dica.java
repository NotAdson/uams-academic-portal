package dica;

import java.util.ArrayList;

public class Dica {
	private String nome, tema;
	private ArrayList<IAnexo> anexos;
	
	public Dica(String nome, String tema){
		this.nome = nome;
		this.tema = tema;
		this.anexos = new ArrayList<>();
	}

	public String getResumo(){
		StringBuilder resumo = new StringBuilder();
		resumo.append(this.nome).append("\n");

		for(IAnexo anexo: this.anexos){
			resumo.append(anexo.getResumo());
		}

		return resumo.toString();
	}

	public String getDetalhado(){
		StringBuilder detalhado = new StringBuilder();
		detalhado.append(this.nome).append("\n");

		for(IAnexo anexo: this.anexos){
			detalhado.append(anexo.getDetalhado());
		}

		return detalhado.toString();
	}
	
	public double adicionarElementoMultimidia(String link, String cabecalho, int duracao){
		IAnexo anexo = new Multimidia(link, cabecalho, duracao);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}

	public double adicionarElementoTexto(String texto){
		IAnexo anexo = new Texto(texto);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}

	public double adicionarElementoReferencia(String titulo, String fonte, boolean verificado, int ano, int relevancia){
		IAnexo anexo = new Referencia(titulo, fonte, verificado, ano, relevancia);
		this.anexos.add(anexo);
		return anexo.calcularBonus();
	}
}
