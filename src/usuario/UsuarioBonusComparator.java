
package usuario;

import java.util.Comparator;

public class UsuarioBonusComparator implements Comparator<Usuario>{
	public int compare(Usuario u1, Usuario u2){
		if(u1.getBonus() > u2.getBonus()){
			return 1;
		}else if(u1.getBonus() < u2.getBonus()){
			return -1;
		}

		return 0;
	}
}
