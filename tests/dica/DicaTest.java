package dica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DicaTest {
	private Dica dica;
	
	@BeforeEach
	void setup() {
		this.dica = new Dica("Jorge", "Façam os testes");
	}
	
	@Test
	void testConstrutorDica() {
		new Dica("Jorge", "Façam os testes");
	}
	
	void testConstrutorNomeNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Dica(null, "Façam os testes");
		});
		
		assertEquals("NOME NULO!", exception.getMessage());
	}
	
	@Test
	void testConstrutorTemaNulo() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> {
			new Dica("Jorge", null);
		});
		
		assertEquals("TEMA NULO!", exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", "  "})
	void testConstrutorNomeVazio(String nome) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Dica(nome, "Façam os testes");
		});
		
		assertEquals("NOME VAZIO!", exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"", "  "})
	void testConstrutorTemaVazio(String tema) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Dica("Jorge", tema);
		});
		
		assertEquals("TEMA VAZIO!", exception.getMessage());
	}
}
