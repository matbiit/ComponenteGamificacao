package br.ita;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlacarTest {
	
	@Test
	public void testRegistrarPontoParaUsuario() {
		Placar p = new Placar(new MockArmazenamento());
		HashMap<Ponto, Integer> tabelaDePontosEsperado = new HashMap<Ponto, Integer>();
		tabelaDePontosEsperado.put(Ponto.CURTIDA, 10);
		p.registrarPontoParaUsuario("matheus", Ponto.CURTIDA, 10);
		HashMap<Ponto, Integer> tabelaDePontos = p.recuperarTodosPontosdDeUsuario("matheus");
		assertEquals(tabelaDePontosEsperado, tabelaDePontos);
	}

	@Test
	public void testRetornarTodosPontosDeUsuario() {
		Placar p = new Placar(new MockArmazenamento());
		HashMap<Ponto, Integer> tabelaDePontosEsperado = new HashMap<Ponto, Integer>();
		tabelaDePontosEsperado.put(Ponto.CURTIDA, 10);
		tabelaDePontosEsperado.put(Ponto.ESTRELA, 5);
		p.registrarPontoParaUsuario("matheus", Ponto.CURTIDA, 10);
		p.registrarPontoParaUsuario("matheus", Ponto.ESTRELA, 5);
		HashMap<Ponto, Integer> tabelaDePontos = p.recuperarTodosPontosdDeUsuario("matheus");
		assertEquals(tabelaDePontosEsperado, tabelaDePontos);
	}
	
	@Test
	public void testRanking() {
		Map<String, Integer> rankingEsperado = new HashMap<String, Integer>();
		rankingEsperado.put("r", 12);
		rankingEsperado.put("matheus", 10);
		rankingEsperado.put("b", 5);
		Placar p = new Placar(new MockArmazenamento());
		p.registrarPontoParaUsuario("matheus", Ponto.CURTIDA, 10);
		p.registrarPontoParaUsuario("b", Ponto.CURTIDA, 5);
		p.registrarPontoParaUsuario("r", Ponto.CURTIDA, 12);
		Map<String, Integer> ranking = p.rankingDePonto(Ponto.CURTIDA);
		assertEquals(rankingEsperado, ranking);
	}
	

}
