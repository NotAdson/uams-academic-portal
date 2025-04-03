package atividade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ExtensaoTest {

    @Test
    void testConstrutorValido() {
        Extensao extensao = new Extensao("CPF_POS", 36, "Projeto Social");
        assertEquals("Projeto Social", extensao.getSubtipo());
        assertEquals(30, extensao.getCreditos());
    }

    @Test
    void testConstrutorSubtipoNulo() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Extensao("CPF_POS", 20, null);
        });
        
        assertEquals("SUBTIPO NULO!", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testSubtipoVazio(String subtipo) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Extensao("CPF_POS", 15, subtipo);
        });
        
        assertEquals("SUBTIPO VAZIO!", e.getMessage());
    }

    @Test
    void testTempoNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Extensao("CPF_POS", -5, "Pesquisa Acadêmica");
        });
        
        assertEquals("TEMPO MENOR QUE 0", e.getMessage());
    }
}