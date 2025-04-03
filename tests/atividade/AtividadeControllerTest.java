package atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import usuario.UsuarioController;

class AtividadeControllerTest {

    private AtividadeController ac;
    private UsuarioController uc;

    @BeforeEach
    void setUp() {
        uc = new UsuarioController();
        ac = new AtividadeController();
        
        uc.criarUsuario("NOME", "123.456.789-10", "senha123", "124110000");
    }

    @Test
    void testCriarAtividadePesquisaExtensaoValida() {
        String codigo = ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 10, "Projeto Social", uc);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadePesquisaExtensaoSubtipoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 10, "", uc);
        });
    }

    @Test
    void testCriarAtividadeEstagioValida() {
        String codigo = ac.criarAtividadeEstagio("123.456.789-10", "senha123", 300, "Empresa XYZ", uc);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeEstagioHorasInsuficientes() {
        String resultado = ac.criarAtividadeEstagio("123.456.789-10", "senha123", 299, "Empresa ABC", uc);
        assertEquals("ABAIXO DO MINIMO DE HORAS", resultado);
    }

    @Test
    void testCriarAtividadeRepresentacaoValida() {
        String codigo = ac.criarAtividadeRepresentacao("123.456.789-10", "senha123", 1, "Diretório Acadêmico", uc);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeRepresentacaoAnosInsuficientes() {
        String resultado = ac.criarAtividadeRepresentacao("123.456.789-10", "senha123", 0, "Conselho Departamental", uc);
        assertEquals("ABAIXO DO MINIMO DE ANOS", resultado);
    }

    @Test
    void testCriarAtividadeMonitoriaValida() {
        String codigo = ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 1, "C1", uc);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeMonitoriaSemestresInsuficientes() {
        String resultado = ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 0, "Física", uc);
        assertEquals("ABAIXO DO MINIMO DE SEMESTRES", resultado);
    }

    @Test
    void testAlterarDescricaoAtividadeValida() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 10, "Projeto", uc);
        String codigo = "123.456.789-10_0";
        boolean resultado = ac.alterarDescricaoAtividade("123.456.789-10", "senha123", codigo, "Nova descrição", uc);
        assertTrue(resultado);
    }

    @Test
    void testAlterarDescricaoAtividadeCodigoInvalido() {
        boolean resultado = ac.alterarDescricaoAtividade("123.456.789-10", "senha123", "codigo_invalido", "Descrição", uc);
        assertFalse(resultado);
    }

    @Test
    void testAlterarComprovacaoAtividadeValida() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 10, "Projeto", uc);
        String codigo = "123.456.789-10_0";
        boolean resultado = ac.alterarComprovacaoAtividade("123.456.789-10", "senha123", codigo, "http://comprovacao.com", uc);
        assertTrue(resultado);
    }

    @ParameterizedTest
    @CsvSource({
        "PESQUISA_EXTENSAO, 12, 10",
        "MONITORIA, 1, 4",
        "ESTAGIO, 300, 5",
        "REPRESENTACAO_ESTUDANTIL, 1, 2"
    })
    void testGetCreditoAtividade(String tipo, int unidades, int esperado) {
        switch(tipo) {
            case "PESQUISA_EXTENSAO":
                ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", unidades, "Subtipo", uc);
                break;
            case "MONITORIA":
                ac.criarAtividadeMonitoria("123.456.789-10", "senha123", unidades, "Disciplina", uc);
                break;
            case "ESTAGIO":
                ac.criarAtividadeEstagio("123.456.789-10", "senha123", unidades, "Empresa", uc);
                break;
            case "REPRESENTACAO_ESTUDANTIL":
                ac.criarAtividadeRepresentacao("123.456.789-10", "senha123", unidades, "Subtipo", uc);
                break;
        }
        
        int creditos = ac.getCreditoAtividade("123.456.789-10", "senha123", tipo, uc);
        assertEquals(esperado, creditos);
    }

    @Test
    void testGetTotalCreditos() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 10, "Projeto", uc);
        ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 1, "C1", uc);
        
        int total = ac.getTotalCreditos("123.456.789-10");
        assertTrue(total > 0);
    }

    @Test
    void testIsMetaAlcancada() {
        ac.criarAtividadeEstagio("123.456.789-10", "senha123", 300, "Empresa", uc);
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 12, "Projeto", uc);
        ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 4, "P1", uc);
        
        boolean metaAlcancada = ac.isMetaAlcancada("123.456.789-10", "senha123", uc);
        assertTrue(metaAlcancada);
    }
    
    @Test
    void testIsMetaNaoAlcancada() {
        ac.criarAtividadeEstagio("123.456.789-10", "senha123", 300, "Empresa", uc);
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 12, "Projeto", uc);
        
        boolean metaAlcancada = ac.isMetaAlcancada("123.456.789-10", "senha123", uc);
        assertFalse(metaAlcancada);
    }

    @Test
    void testGerarMapaAtividade() {
        ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 2, "LP2", uc);
        String mapa = ac.gerarMapaAtividade("123.456.789-10", "senha123", "MONITORIA", uc);
        assertTrue(mapa.contains("8/16"));
    }

    @Test
    void testGerarMapaCreditos() {
        ac.criarAtividadeMonitoria("123.456.789-10", "senha123", 5, "P2", uc);
        ac.criarAtividadePesquisaExtensao("123.456.789-10", "senha123", 12, "Projeto", uc);
        
        String mapa = ac.gerarMapaCreditos("123.456.789-10", "senha123", uc);
        assertEquals("REPRESENTACAO_ESTUDANTIL: 0/2\nMONITORIA: 16/16\nESTAGIO: 0/18\nPESQUISA_EXTENSAO: 10/18\n", mapa);
    }
}