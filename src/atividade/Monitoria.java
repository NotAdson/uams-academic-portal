package atividade;
	
import middleware.Validator;

public class Monitoria extends Atividade{
	private String disciplina;

	public Monitoria(String codigo, int tempo, String disciplina){
		super(codigo, "MONITORIA", 4, tempo);

		Validator.verifyStringBlank(codigo, "CODIGO");
		Validator.verifyStringBlank(disciplina, "DISCIPLINA");
		
		if(tempo < 0){
			throw new IllegalArgumentException("TEMPO MENOR QUE 0");
		}
		this.disciplina = disciplina;
	}

	public String getDisciplina(){
		return this.disciplina;
	}

	public void setDisciplina(String disciplina){
		this.disciplina = disciplina;
	}
}
