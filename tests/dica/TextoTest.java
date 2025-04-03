package dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TextoTest {

	@Test
	void testConstrutorValido() {
		new Texto("As vacas amarelas ...");
	}
	
	@Test
	void testConstrutorTextoNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Texto(null);
		});
		
		assertEquals("TEXTO NULO!", exception.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testTextoVazio(String texto) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Texto(texto);
		});
		
		assertEquals("TEXTO VAZIO!", exception.getMessage());
	}
	
	@Test
	void testGetResumo() {
		Texto texto = new Texto("As vacas amarelas ...");
		assertEquals("As vacas amarelas ...", texto.getResumo());
	}
	
	@Test
	void testGetDetalhado() {
		Texto texto = new Texto("A");
		assertEquals("A\n1", texto.getDetalhado());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 99})
	void testCalcularBonusMenorQue100(int count) {
		Texto texto = new Texto("A".repeat(count));
		assertEquals(0, texto.calcularBonus());
	}
	
	@ParameterizedTest
	@CsvSource({
		"100, 10",
		"1000, 50",
		"1000000000, 50"
	})
	void testCalcularBonusMaiorQue100(int count, double bonus) {
		Texto texto = new Texto("A".repeat(count));
		assertEquals(bonus, texto.calcularBonus());
	}
}
