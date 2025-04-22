package middleware;

/**
 * A classe {@code Validator} fornece métodos utilitários para validação de strings.
 * Ela contém métodos para verificar se uma string contém apenas dígitos e para garantir que uma string não seja nula ou vazia.
 */
public class Validator {

	/**
	 * Verifica se uma string contém apenas caracteres numéricos (dígitos).
	 *
	 * @param str A string a ser verificada.
	 * @return {@code true} se a string contiver apenas dígitos, se não, {@code false}, e se a String for nula ou vazia.
	 */
	public static boolean isStringDigit(String str) {
		if (str == null || str.isBlank()) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifica se uma string é nula ou vazia (após remoção de espaços em branco).
	 * Se a string for nula, uma exceção {@code NullPointerException} é lançada.
	 * Se a string for vazia, uma exceção {@code IllegalArgumentException} é lançada.
	 *
	 * @param str      A string a ser verificada.
	 * @param nomeArg  O nome do argumento que está sendo verificado (usado na mensagem de exceção).
	 * @throws NullPointerException      Se a string for nula.
	 * @throws IllegalArgumentException Se a string for vazia.
	 */
	public static void verifyStringBlank(String str, String nomeArg) {
		if (str == null) {
			throw new NullPointerException(nomeArg + " NULO!");
		} else if (str.isBlank()) {
			throw new IllegalArgumentException(nomeArg + " VAZIO!");
		}
	}
	
	public static int countDigit(String string) {
		int counter = 0;
		for(int i = 0; i < string.length(); i++) {
			if(Character.isDigit(string.charAt(i))){
				counter++;
			}
		}
		
		return counter;
	}
}
