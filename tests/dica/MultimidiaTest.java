package dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MultimidiaTest {

	@Test
	void testConstrutorValido() {
		new Multimidia("link", "cabecalho", 5);
	}
	
	@Test
	void testConstrutorLinkNulo() {
		Exception e = assertThrows(NullPointerException.class, () -> {
			new Multimidia(null, "cabecalho", 3);
		});
		
		assertEquals("LINK NULO!", e.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorLinkVazio(String link) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Multimidia(link, "cabecalho", 3);
		});
		
		assertEquals("LINK VAZIO!", exception.getMessage());
	}
	
	@Test
	void testConstrutorCabecalhoNulo() {
		Exception e = assertThrows(NullPointerException.class, () -> {
			new Multimidia("link", null, 3);
		});
		
		assertEquals("CABECALHO NULO!", e.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void testConstrutorCabecalhoVazio(String cabecalho) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Multimidia("link", cabecalho, 3);
		});
		
		assertEquals("CABECALHO VAZIO!", exception.getMessage());
	}
	
	@Test
	void testGetResumo() {
		Multimidia multimidia = new Multimidia("link", "cabecalho", 3);
		assertEquals("link\ncabecalho", multimidia.getResumo());
	}
	
	@Test
	void testGetDetalhado() {
		Multimidia multimidia = new Multimidia("link", "cabecalho", 3);
		assertEquals("link\n3s\ncabecalho", multimidia.getDetalhado());		
	}
	
	@ParameterizedTest
	@CsvSource({
		"120, 10",
		"3500, 50",
		"240, 20"
	})
	void testCalcularBonus(int segundos, int bonus) {
		Multimidia multimidia = new Multimidia("link", "cabecalho", segundos);
		assertEquals(bonus, multimidia.calcularBonus());
	}
	

}
