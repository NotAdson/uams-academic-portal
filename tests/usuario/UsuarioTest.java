package usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UsuarioTest {
    private Usuario usuario;
    private final String CPF_VALIDO = "123.456.789-09";
    private final String SENHA_VALIDA = "senha123";
    private final String NOME_VALIDO = "Nome Completo";
    private final String MATRICULA_VALIDA = "20230001";

    @BeforeEach
    public void setup() {
        this.usuario = new Usuario(CPF_VALIDO, SENHA_VALIDA, NOME_VALIDO, MATRICULA_VALIDA);
    }

    @Test
    public void testVerificaSenhaCorreta() {
        assertTrue(this.usuario.verificaSenha(SENHA_VALIDA));
    }

    @Test
    public void testVerificaSenhaErrada() {
        assertFalse(this.usuario.verificaSenha("senhaErrada"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void testVerificaSenhaInvalida(String senhaInvalida) {
        assertThrows(IllegalArgumentException.class, () -> {
            this.usuario.verificaSenha(senhaInvalida);
        });
    }

    @Test
    public void testSetSenhaValida() {
        String novaSenha = "novaSenha123";
        usuario.setSenha(novaSenha);
        assertTrue(usuario.verificaSenha(novaSenha));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testSetSenhaInvalida(String senhaInvalida) {
        assertThrows(IllegalArgumentException.class, () -> {
            usuario.setSenha(senhaInvalida);
        });
    }

    @Test
    public void testGetNome() {
        assertEquals(NOME_VALIDO, usuario.getNome());
    }

    @Test
    public void testGetCpf() {
        assertEquals(CPF_VALIDO, usuario.getCpf());
    }
    
    @Test
    public void testCompareMaior() {
    	Usuario u1 = new Usuario("000.000.000-00", SENHA_VALIDA, "A", MATRICULA_VALIDA);
    	Usuario u2 = new Usuario("111.111.111-11", SENHA_VALIDA, "B", MATRICULA_VALIDA);
    	
    	assertEquals(-1, u1.compareTo(u2));
    }
    
    @Test
    public void testCompareMenor() {
    	Usuario u1 = new Usuario("000.000.000-00", SENHA_VALIDA, "B", MATRICULA_VALIDA);
    	Usuario u2 = new Usuario("111.111.111-11", SENHA_VALIDA, "A", MATRICULA_VALIDA);
    	
    	assertEquals(1, u1.compareTo(u2));
    }
    
    @Test
    public void testCompareIgual() {
    	Usuario u1 = new Usuario("000.000.000-00", SENHA_VALIDA, "A", MATRICULA_VALIDA);
    	Usuario u2 = new Usuario("111.111.111-11", SENHA_VALIDA, "A", MATRICULA_VALIDA);
    	
    	assertEquals(0, u1.compareTo(u2));
    }
}