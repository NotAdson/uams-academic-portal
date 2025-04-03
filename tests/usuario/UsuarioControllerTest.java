package usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UsuarioControllerTest {

    private UsuarioController uc;
    private final String CPF_VALIDO = "123.456.789-09";
    private final String SENHA_VALIDA = "12345678";
    private final String NOME_VALIDO = "Seu Hélio";
    private final String MATRICULA_VALIDA = "124110000";

    @BeforeEach
    void setUp() {
        uc = new UsuarioController();
        uc.criarUsuario(NOME_VALIDO, CPF_VALIDO, SENHA_VALIDA, MATRICULA_VALIDA);
    }

    @Test
    void testCriarUsuarioValido() {
        boolean resultado = uc.criarUsuario(NOME_VALIDO, CPF_VALIDO, SENHA_VALIDA, MATRICULA_VALIDA);
        assertTrue(resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12345678901234", "123.456.789-0"})
    void testCriarUsuarioCpfInvalido(String cpfInvalido) {
        boolean resultado = uc.criarUsuario("Usuário Inválido", cpfInvalido, SENHA_VALIDA, MATRICULA_VALIDA);
        assertFalse(resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "123456789"})
    void testCriarUsuarioSenhaInvalida(String senhaInvalida) {
        boolean resultado = uc.criarUsuario("Usuário Inválido", "987.654.321-00", senhaInvalida, MATRICULA_VALIDA);
        assertFalse(resultado);
    }

    @Test
    void testCriarUsuarioDuplicado() {
        boolean resultado = uc.criarUsuario(NOME_VALIDO, CPF_VALIDO, SENHA_VALIDA, MATRICULA_VALIDA);
        assertFalse(resultado);
    }

    @Test
    void testAutenticarUsuarioValido() {
        assertDoesNotThrow(() -> uc.autenticarUsuario(CPF_VALIDO, SENHA_VALIDA));
    }

    @Test
    void testAutenticarUsuarioSenhaIncorreta() {
        assertThrows(IllegalArgumentException.class, () -> {
            uc.autenticarUsuario(CPF_VALIDO, "senhaincorreta");
        });
    }

    @Test
    void testAutenticarUsuarioCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            uc.autenticarUsuario("", SENHA_VALIDA);
        });
    }

    @Test
    void testAlterarSenhaUsuario() {
        String novaSenha = "87654321";
        boolean resultado = uc.alterarSenhaUsuario(CPF_VALIDO, SENHA_VALIDA, novaSenha);
        assertTrue(resultado);
        
        assertDoesNotThrow(() -> uc.autenticarUsuario(CPF_VALIDO, novaSenha));
    }

    @Test
    void testGetNomeUsuario() {
        String nome = uc.getNomeUsuario(CPF_VALIDO);
        assertEquals(NOME_VALIDO, nome);
    }

    @Test
    void testAdicionarBonus() {
        int bonusInicial = 0;
        int bonusAdicional = 10;
        
        uc.adicionarBonus(CPF_VALIDO, bonusAdicional);
        
        assertTrue(uc.listarRankingBonus()[0].contains(String.valueOf(bonusInicial + bonusAdicional)));
    }

    @Test
    void testGetUsuarioExistente() {
        String resultado = uc.getUsuario(CPF_VALIDO);
        assertTrue(resultado.contains(NOME_VALIDO));
    }

    @Test
    void testGetUsuarioInexistente() {
        String resultado = uc.getUsuario("000.000.000-00");
        assertEquals("USUÁRIO NÃO EXISTE", resultado);
    }

    @Test
    void testExibirUsuariosOrdenados() {
        uc.criarUsuario("Aardvark", "111.111.111-11", "11111111", "20230002");
        uc.criarUsuario("Zebra", "999.999.999-99", "99999999", "20230003");
        
        String[] usuarios = uc.exibirUsuarios();
        assertTrue(usuarios[0].contains("Aardvark"));
        assertTrue(usuarios[2].contains("Zebra"));
    }

    @Test
    void testListarRankingBonus() {
        String cpf2 = "111.111.111-11";
        String cpf3 = "222.222.222-22";
        
        uc.criarUsuario("Usuário 2", cpf2, "11111111", "20230002");
        uc.criarUsuario("Usuário 3", cpf3, "22222222", "20230003");
        
        uc.adicionarBonus(CPF_VALIDO, 5);
        uc.adicionarBonus(cpf2, 10);
        uc.adicionarBonus(cpf3, 2);
        
        String[] ranking = uc.listarRankingBonus();
        assertTrue(ranking[0].contains("10"));
        assertTrue(ranking[1].contains("5"));
        assertTrue(ranking[2].contains("2"));
    }
}