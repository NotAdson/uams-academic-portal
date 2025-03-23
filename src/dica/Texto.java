package dica;

import middleware.Validator;

/**
 * A classe {@code Texto} representa um anexo do tipo texto que pode ser adicionado a uma dica.
 * Ela implementa a interface {@code IAnexo} e fornece métodos para gerar resumos, detalhes e calcular o bônus associado ao anexo.
 */
public class Texto implements IAnexo {
	private String texto;

	public Texto(String texto) {
		Validator.verifyStringBlank(texto, "TEXTO");
		this.texto = texto;
	}

	@Override
	public String getResumo() {
		return this.texto;
	}

	@Override
	public String getDetalhado() {
		return this.texto + "\n" + this.texto.length();
	}

	@Override
	public double calcularBonus() {
		if (this.texto.length() < 100) {
			return 0;
		}
		return this.texto.length() / 10;
	}
}
