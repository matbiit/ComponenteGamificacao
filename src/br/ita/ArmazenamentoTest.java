package br.ita;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ArmazenamentoTest {


	@Test
	public void testSalavarERecuperar() {
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.salvarPontosDeUsuario("matheus", Ponto.MOEDA, 10);
		assertEquals(20, new Double(armazenamento.recuperarPontosDeUsuario("matheus", Ponto.MOEDA)), 0.001);
	}
	
	@Test
	public void testUsuariosComPontos() {
		Armazenamento armazenamento = new Armazenamento();
		ArrayList<String> usuarios = new ArrayList<String>();
		usuarios.add("matheus");
		armazenamento.salvarPontosDeUsuario("matheus", Ponto.MOEDA, 10);
		assertEquals(usuarios, armazenamento.getUsuariosComPontos());
	}
	
	@Test
	public void testTodosPontosDeUsuario() {
		Armazenamento armazenamento = new Armazenamento();
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();
		pontos.add(Ponto.MOEDA);
		armazenamento.salvarPontosDeUsuario("matheus", Ponto.MOEDA, 10);
		assertEquals(pontos, armazenamento.getTodosPontosDeUsuario("matheus"));
	}

}
