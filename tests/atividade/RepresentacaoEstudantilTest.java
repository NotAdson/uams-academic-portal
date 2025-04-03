package atividade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RepresentacaoEstudantilTest {

    @Test
    void testConstrutorValido() {
        RepresentacaoEstudantil rep = new RepresentacaoEstudantil("CPF_POS", 1, "Diretório Acadêmico");
        assertEquals("Diretório Acadêmico", rep.getSubTipo());
        assertEquals(2, rep.getCreditos());
    }

    @Test
    void testConstrutorSubTipoNulo() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new RepresentacaoEstudantil("CPF_POS", 40, null);
        });
        
        assertEquals("SUBTIPO NULO!", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testSubTipoVazio(String subTipo) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new RepresentacaoEstudantil("CPF_POS", 30, subTipo);
        });
        
        assertEquals("SUBTIPO VAZIO!", e.getMessage());
    }

    @Test
    void testTempoNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new RepresentacaoEstudantil("CPF_POS", -5, "Conselho Departamental");
        });
        
        assertEquals("TEMPO MENOR QUE 0", e.getMessage());
    }
}