package dica;

import java.util.ArrayList;

public class DicaController {
	private ArrayList<Dica> dicas;

	public DicaController(){
		this.dicas =  new ArrayList<>();
	}

	public int adicionarDica(String nome, String tema){
		Dica dica = new Dica(nome, tema);
		dicas.add(dica);

		return dicas.size() - 1;
	}

	public double adicionarElemetoTextoDica(int posicao, String texto){
		return this.dicas.get(posicao).adicionarElementoTexto(texto);
	}

	public double adicionarElementoMultimidiaDica(int posicao, String link, String cabecalho, int tempo){
		return this.dicas.get(posicao).adicionarElementoMultimidia(link, cabecalho, tempo);
	}

	public double adicionarElementoReferenciaDica(int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia){
		return this.dicas.get(posicao).adicionarElementoReferencia(titulo, fonte, conferida, ano, importancia);
	}
}
