package atividade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EstagioTest {

    @Test
    void testConstrutorValido() {
        Estagio estagio = new Estagio("CPF_POS", 300, "Empresa");
        assertEquals("Empresa", estagio.getEmpresa());
        assertEquals(5, estagio.getCreditos());
    }

    @Test
    void testConstrutorEmpresaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Estagio("CPF_POS", 300, null);
        });
        
        assertEquals("EMPRESA NULO!", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testEmpresaVazia(String empresa) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Estagio("CPF_POS", 300, empresa);
        });
        
        assertEquals("EMPRESA VAZIO!", e.getMessage());
    }

    @Test
    void testTempoNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Estagio("CPF_POS", -1, "Empresa DEF");
        });
        
        assertEquals("TEMPO MENOR QUE 0", e.getMessage());
    }
}