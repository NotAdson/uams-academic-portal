package dica;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import usuario.UsuarioController;

class DicaControllerTest {

	private DicaController dc;
	private UsuarioController uc;

	@BeforeEach
	void setUp() {
		this.uc = new UsuarioController();
		this.dc = new DicaController();
		
		uc.criarUsuario("NOME", "123.456.789-10", "senha123", "124110000");
	}

	@Test
	void testAdicionarDicaValida() {
		int posicao = dc.adicionarDica("123.456.789-10", "senha123", "Tema Importante", uc);
		assertEquals(0, posicao);
	}

	@Test
	void testAdicionarDicaTemaNulo() {
		assertThrows(NullPointerException.class, () -> {
			dc.adicionarDica("123.456.789-10", "senha123", null, uc);
		});
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "   "})
	void testAdicionarDicaTemaVazio(String tema) {
		assertThrows(IllegalArgumentException.class, () -> {
			dc.adicionarDica("123.456.789-10", "senha123", tema, uc);
		});
	}

	@Test
	void testAdicionarElementoTextoValido() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		boolean resultado = dc.adicionarElemetoTextoDica("123.456.789-10", "senha123", 0, "Texto válido", uc);
		assertTrue(resultado);
	}

	@Test
	void testAdicionarElementoTextoLongo() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		boolean resultado = dc.adicionarElemetoTextoDica("123.456.789-10", "senha123", 0, "a".repeat(501), uc);
		assertFalse(resultado);
	}

	@Test
	void testAdicionarElementoMultimidiaValido() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		boolean resultado = dc.adicionarElementoMultimidiaDica("123.456.789-10", "senha123", 0, "http://exemplo.com", "Cabeçalho", 120, uc);
		assertTrue(resultado);
	}

	@Test
	void testAdicionarElementoReferenciaValido() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		boolean resultado = dc.adicionarElementoReferenciaDica("123.456.789-10", "senha123", 0, "Título", "Fonte", 2023, true, 3, uc);
		assertTrue(resultado);
	}

	@Test
	void testListarDicas() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema 1", uc);
		dc.adicionarDica("123.456.789-10", "senha123", "Tema 2", uc);

		String[] dicas = dc.listarDicas();
		assertEquals(2, dicas.length);
	}

	@Test
	void testListarDicasDetalhes() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		dc.adicionarElemetoTextoDica("123.456.789-10", "senha123", 0, "Texto", uc);

		String[] detalhes = dc.listarDicasDetalhes();
		assertTrue(detalhes[0].contains("Texto"));
	}

	@Test
	void testListarDicaPorPosicao() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema Específico", uc);
		String resumo = dc.listarDica(0);
		assertTrue(resumo.contains("NOME"));
	}

	@Test
	void testListarDicaDetalhesPorPosicao() {
		dc.adicionarDica("123.456.789-10", "senha123", "Tema", uc);
		dc.adicionarElementoMultimidiaDica("123.456.789-10", "senha123", 0, "http://link.com", "Vídeo", 300, uc);

		String detalhes = dc.listarDicaDetalhes(0);
		assertTrue(detalhes.contains("Vídeo"));
	}

	@Test
	void testPosicaoInvalida() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			dc.listarDica(999);
		});
	}
}
