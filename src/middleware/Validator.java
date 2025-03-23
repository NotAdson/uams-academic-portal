package middleware;

public class Validator {
	public static boolean isStringDigit(String str){
		if(str == null || str.isBlank()){
			return false;
		}

		for(int i = 0; i < str.length(); i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public static void verifyStringBlank(String str, String nomeArg){
		if(str == null){
			throw new NullPointerException(nomeArg + " NULO!");
		}else if(str.isBlank()){
			throw new IllegalArgumentException(nomeArg + " VAZIO!");
		}
	}
}
