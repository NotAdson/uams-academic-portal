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
        ac.criarAtividadePesquisaExtensao(CPF_VALIDO, 12, "PIVITI");
        ac.criarAtividadeMonitoria(CPF_VALIDO, 1, "Disciplina");

    }

    @Test
    void testGerarRelatorioFinalCreditosSuficientes() {
    	ac.criarAtividadePesquisaExtensao(CPF_VALIDO, 12, "PET");
    	ac.criarAtividadePesquisaExtensao(CPF_VALIDO, 12, "PROBEX");
        String resultado = rc.gerarRelatorioFinal(CPF_VALIDO, uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("TOTAL: 22"));
    }

    @Test
    void testGerarRelatorioFinalPorAtividadeCreditosSuficientes() {
    	ac.criarAtividadePesquisaExtensao(CPF_VALIDO, 12, "PET");
    	ac.criarAtividadePesquisaExtensao(CPF_VALIDO, 12, "PIBIC");
        String resultado = rc.gerarRelatorioFinalPorAtividade(CPF_VALIDO, "MONITORIA", uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("10"));
    }
    
    @Test
    void testGerarRelatorioFinalCreditosInsuficientes() {
        String resultado = rc.gerarRelatorioFinal(CPF_VALIDO, uc, ac);
        assertEquals("META NÃO ALCANÇADA", resultado);
    }

    @Test
    void testGerarRelatorioFinalPorAtividadeCreditosInsuficientes() {
        String resultado = rc.gerarRelatorioFinalPorAtividade(CPF_VALIDO, "MONITORIA", uc, ac);
        
        assertEquals("META NÃO ALCANÇADA", resultado);
    }

    @Test
    void testGerarRelatorioParcialNaoSalvar() {
        String resultado = rc.gerarRelatorioParcial(CPF_VALIDO, false, uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(rc.listarHistorico(CPF_VALIDO).isEmpty());
    }

    @Test
    void testGerarRelatorioParcialSalvar() {
        String resultado = rc.gerarRelatorioParcial(CPF_VALIDO, true, uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertFalse(rc.listarHistorico(CPF_VALIDO).isEmpty());
    }

    @Test
    void testGerarRelatorioParcialPorAtividadeSalvar() {
        String resultado = rc.gerarRelatorioParcialPorAtividade(CPF_VALIDO, true, "PESQUISA_EXTENSAO", uc, ac);
        
        assertTrue(resultado.contains("NOME"));
        assertTrue(resultado.contains("10/18"));
        assertFalse(rc.listarHistorico(CPF_VALIDO).isEmpty());
    }

    @Test
    void testExcluirItemHistoricoExistente() {
        rc.gerarRelatorioParcial(CPF_VALIDO, true, uc, ac);
        String data = LocalDate.now().toString();
        
        boolean resultado = rc.excluirItemHistorico(CPF_VALIDO, data);
        assertTrue(resultado);
        assertTrue(rc.listarHistorico(CPF_VALIDO).isEmpty());
    }

    @Test
    void testExcluirItemHistoricoInexistente() {
        boolean resultado = rc.excluirItemHistorico(CPF_VALIDO, "2023-01-01");
        assertFalse(resultado);
    }

    @Test
    void testListarHistoricoVazio() {
        String resultado = rc.listarHistorico(CPF_VALIDO);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testListarHistoricoComItens() {
        rc.gerarRelatorioParcial(CPF_VALIDO, true, uc, ac);
        rc.gerarRelatorioParcialPorAtividade(CPF_VALIDO, true, "MONITORIA", uc, ac);
        
        String resultado = rc.listarHistorico(CPF_VALIDO);
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.contains("NOME"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void testAutenticacaoComCpfInvalido(String cpfInvalido) {        
        assertThrows(IllegalArgumentException.class, () -> {
            rc.gerarRelatorioFinal(cpfInvalido, uc, ac);
        });
    }
}
