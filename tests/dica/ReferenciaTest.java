package dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReferenciaTest {
	@Test
	void testConstrutorReferencia() {
		new Referencia("Vacas amarelas", "www.wikipedia.net", false, 2015, 5);
	}
	
	@Test
	void testConstrutorTituloNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Referencia(null, "www.wikipedia.net", false, 2015, 5);
		});
		
		assertEquals("TITULO NULO!", exception.getMessage());
	}
	
	void testConstrutorFonteNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Referencia("Vaca amarela", null, false, 2015, 5);
		});
		
		assertEquals("FONTE NULO!", exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", "  "})
	void testConstrutorTituloVazio(String titulo) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia(titulo, "www.wikipedia.net", false, 2015, 5);
		});
		
		assertEquals("TITULO VAZIO!", exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", "  "})
	void testConstrutorFonteVazio(String fonte) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("Vaca amarela", fonte, false, 2015, 5);
		});
		
		assertEquals("FONTE VAZIO!", exception.getMessage());
	}
	
	@Test
	void testGetResumo() {
		Referencia verificada = new Referencia("Vacas amarelas", "www.wikipedia.net", true, 2015, 4);
		assertEquals("Vacas amarelas\nwww.wikipedia.net\n2015", verificada.getResumo());
	}

	@Test
	void testGetDetalhadoVerificada() {
		Referencia verificada = new Referencia("Vacas amarelas", "www.wikipedia.net", true, 2015, 4);
		assertEquals("Vacas amarelas\nwww.wikipedia.net\n4/5\nverificado: Sim\n2015", verificada.getDetalhado());
	}
	
	@Test
	void testGetDetalhadoNaoVerificada() {
		Referencia NVerificada = new Referencia("Vacas amarelas", "www.wikipedia.net", false, 2015, 4);
		assertEquals("Vacas amarelas\nwww.wikipedia.net\n4/5\nverificado: Não\n2015", NVerificada.getDetalhado());
	}
	
	@Test
	void testCalcularBonusVerificada() {
		Referencia verificada = new Referencia("Vacas amarelas", "www.wikipedia.net", true, 2015, 4);
		assertEquals(15, verificada.calcularBonus());
	}
	
	@Test
	void testCalcularBonusNaoVerificada() {
		Referencia NVerificada = new Referencia("Vacas amarelas", "www.wikipedia.net", false, 2015, 4);
		assertEquals(0, NVerificada.calcularBonus());
	}
}
