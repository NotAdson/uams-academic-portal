package atividade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"PET", "PIBIC", "PIVIC", "PIBITI", "PIVITI", "PROBEX", "PDI"})
    void testCriarAtividadePesquisaExtensaoValida(String subtipo) {
        String codigo = ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, subtipo);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }
    
    @Test
    void testCriarAtividadePesquisaExtensaoSubtipoInvalido() {
        assertEquals("ATIVIDADE NÃO CADASTRADA", ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, "SUBTIPO INVÁLIDO"));
    }

    @Test
    void testCriarAtividadePesquisaExtensaoSubtipoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, "");
        });
    }

    @Test
    void testCriarAtividadeEstagioValida() {
        String codigo = ac.criarAtividadeEstagio("123.456.789-10", 300, "Empresa XYZ");
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeEstagioHorasInsuficientes() {
        String resultado = ac.criarAtividadeEstagio("123.456.789-10", 299, "Empresa ABC");
        assertEquals("ATIVIDADE NÃO CADASTRADA", resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = {"DIRETORIA", "COMISSAO"})
    void testCriarAtividadeRepresentacaoValida(String subtipo) {
        String codigo = ac.criarAtividadeRepresentacao("123.456.789-10", 1, subtipo);
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeRepresentacaoAnosInsuficientes() {
        String resultado = ac.criarAtividadeRepresentacao("123.456.789-10", 0, "COMISSAO");
        assertEquals("ABAIXO DO MINIMO DE ANOS", resultado);
    }

    @Test
    void testCriarAtividadeMonitoriaValida() {
        String codigo = ac.criarAtividadeMonitoria("123.456.789-10", 1, "C1");
        assertNotNull(codigo);
        assertTrue(codigo.startsWith("123.456.789-10_"));
    }

    @Test
    void testCriarAtividadeMonitoriaSemestresInsuficientes() {
        String resultado = ac.criarAtividadeMonitoria("123.456.789-10", 0, "Física");
        assertEquals("ABAIXO DO MINIMO DE SEMESTRES", resultado);
    }

    @Test
    void testAlterarDescricaoAtividadeValida() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, "PIVIC");
        String codigo = "123.456.789-10_0";
        boolean resultado = ac.alterarDescricaoAtividade("123.456.789-10", codigo, "Nova descrição");
        assertTrue(resultado);
    }

    @Test
    void testAlterarDescricaoAtividadeCodigoInvalido() {
        boolean resultado = ac.alterarDescricaoAtividade("123.456.789-10", "codigo_invalido", "Descrição");
        assertFalse(resultado);
    }

    @Test
    void testAlterarComprovacaoAtividadeValida() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, "PIBIC");
        String codigo = "123.456.789-10_0";
        boolean resultado = ac.alterarComprovacaoAtividade("123.456.789-10", codigo, "http://comprovacao.com");
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
                ac.criarAtividadePesquisaExtensao("123.456.789-10", unidades, "PIBIC");
                break;
            case "MONITORIA":
                ac.criarAtividadeMonitoria("123.456.789-10", unidades, "Disciplina");
                break;
            case "ESTAGIO":
                ac.criarAtividadeEstagio("123.456.789-10", unidades, "Empresa");
                break;
            case "REPRESENTACAO_ESTUDANTIL":
                ac.criarAtividadeRepresentacao("123.456.789-10", unidades, "DIRETORIA");
                break;
        }
        
        int creditos = ac.getCreditoAtividade("123.456.789-10", tipo);
        assertEquals(esperado, creditos);
    }

    @Test
    void testGetTotalCreditos() {
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 10, "Projeto");
        ac.criarAtividadeMonitoria("123.456.789-10", 1, "C1");
        
        int total = ac.getTotalCreditos("123.456.789-10");
        assertTrue(total > 0);
    }

    @Test
    void testIsMetaAlcancada() {
        ac.criarAtividadeEstagio("123.456.789-10", 300, "Empresa");
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 12, "PIBIC");
        ac.criarAtividadeMonitoria("123.456.789-10", 4, "P1");
        
        boolean metaAlcancada = ac.isMetaAlcancada("123.456.789-10");
        assertTrue(metaAlcancada);
    }
    
    @Test
    void testIsMetaNaoAlcancada() {
        ac.criarAtividadeEstagio("123.456.789-10", 300, "Empresa");
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 12, "Projeto");
        
        boolean metaAlcancada = ac.isMetaAlcancada("123.456.789-10");
        assertFalse(metaAlcancada);
    }

    @Test
    void testGerarMapaAtividade() {
        ac.criarAtividadeMonitoria("123.456.789-10", 2, "LP2");
        String mapa = ac.gerarMapaAtividade("123.456.789-10", "MONITORIA");
        assertTrue(mapa.contains("8/16"));
    }

    @Test
    void testGerarMapaCreditos() {
        ac.criarAtividadeMonitoria("123.456.789-10", 5, "P2");
        ac.criarAtividadePesquisaExtensao("123.456.789-10", 12, "PET");
        
        String mapa = ac.gerarMapaCreditos("123.456.789-10");
        assertEquals("PESQUISA_EXTENSAO: 10/18\nMONITORIA: 16/16\nESTAGIO: 0/18\nREPRESENTACAO_ESTUDANTIL: 0/2\n", mapa);
    }
}
