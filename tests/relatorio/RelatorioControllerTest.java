package relatorio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import usuario.UsuarioController;
import atividade.AtividadeController;

class RelatorioControllerTest {

    private RelatorioController rc;
    private UsuarioController uc;
    private AtividadeController ac;
    private final String CPF_VALIDO = "123.456.789-10";
    private final String SENHA_VALIDA = "senha123";

    @BeforeEach
    void setUp() {
        rc = new RelatorioController();
        uc = new UsuarioController();
        ac = new AtividadeController();
        
        uc.criarUsuario("NOME", CPF_VALIDO, SENHA_VALIDA, "000000000");
        ac.criarAtividadePesquisaExtensao(CPF_VALIDO, SENHA_VALIDA, 12, "Subtipo", uc);
        ac.criarAtividadeMonitoria(CPF_VALIDO, SENHA_VALIDA, 1, "Disciplina", uc);

    }

    @Test
    void testGerarRelatorioFinal() {
        String resultado = rc.gerarRelatorioFinal(CPF_VALIDO, SENHA_VALIDA, uc, ac);
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("TOTAL: 14"));
    }

    @Test
    void testGerarRelatorioFinalPorAtividade() {
        String resultado = rc.gerarRelatorioFinalPorAtividade(CPF_VALIDO, SENHA_VALIDA, "MONITORIA", uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("10"));
    }

    @Test
    void testGerarRelatorioParcialNaoSalvar() {
        String resultado = rc.gerarRelatorioParcial(CPF_VALIDO, SENHA_VALIDA, false, uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc).isEmpty());
    }

    @Test
    void testGerarRelatorioParcialSalvar() {
        String resultado = rc.gerarRelatorioParcial(CPF_VALIDO, SENHA_VALIDA, true, uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertFalse(rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc).isEmpty());
    }

    @Test
    void testGerarRelatorioParcialPorAtividadeSalvar() {
        String resultado = rc.gerarRelatorioParcialPorAtividade(CPF_VALIDO, SENHA_VALIDA, true, "PESQUISA_EXTENSAO", uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("10/18"));
        assertFalse(rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc).isEmpty());
    }

    @Test
    void testExcluirItemHistoricoExistente() {
        rc.gerarRelatorioParcial(CPF_VALIDO, SENHA_VALIDA, true, uc, ac);
        String data = LocalDate.now().toString();
        
        boolean resultado = rc.excluirItemHistorico(CPF_VALIDO, SENHA_VALIDA, data, uc);
        assertTrue(resultado);
        assertTrue(rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc).isEmpty());
    }

    @Test
    void testExcluirItemHistoricoInexistente() {
        boolean resultado = rc.excluirItemHistorico(CPF_VALIDO, SENHA_VALIDA, "2023-01-01", uc);
        assertFalse(resultado);
    }

    @Test
    void testListarHistoricoVazio() {
        String resultado = rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testListarHistoricoComItens() {
        rc.gerarRelatorioParcial(CPF_VALIDO, SENHA_VALIDA, true, uc, ac);
        rc.gerarRelatorioParcialPorAtividade(CPF_VALIDO, SENHA_VALIDA, true, "MONITORIA", uc, ac);
        
        String resultado = rc.listarHistorico(CPF_VALIDO, SENHA_VALIDA, uc);
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.contains("NOME"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "invalid_cpf"})
    void testAutenticacaoComCpfInvalido(String cpfInvalido) {        
        assertThrows(IllegalArgumentException.class, () -> {
            rc.gerarRelatorioFinal(cpfInvalido, SENHA_VALIDA, uc, ac);
        });
    }
}