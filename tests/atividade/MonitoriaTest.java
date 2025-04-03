package atividade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MonitoriaTest {

    @Test
    void testConstrutorValido() {
        Monitoria monitoria = new Monitoria("CPF_POS", 1, "FMCC2");
        assertEquals("FMCC2", monitoria.getDisciplina());
        assertEquals(4, monitoria.getCreditos());
    }

    @Test
    void testConstrutorDisciplinaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Monitoria("CPF_POS", 1, null);
        });
        
        assertEquals("DISCIPLINA NULO!", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void testDisciplinaVazia(String disciplina) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Monitoria("CPF_POS", 1, disciplina);
        });
        
        assertEquals("DISCIPLINA VAZIO!", e.getMessage());
    }

    @Test
    void testTempoNegativo() {
    	Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Monitoria("CPF_POS", -10, "C1");
        });
    	
    	assertEquals("TEMPO MENOR QUE 0", e.getMessage());
    }
}