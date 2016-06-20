package br.ita;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Placar {

	private Armazenamento armazenamento;
	
	
	public Placar(Armazenamento armazenamento){
		this.armazenamento = armazenamento;
	}
	
	protected void registrarPontoParaUsuario(String usuario, Ponto ponto, Integer quantidade){
		armazenamento.salvarPontosDeUsuario(usuario, ponto, quantidade);
	}
	
	protected HashMap<Ponto, Integer> recuperarTodosPontosdDeUsuario(String usuario){
		HashMap<Ponto, Integer> tabelaDePontos = new HashMap<Ponto, Integer>();
		List<Ponto> pontos = armazenamento.getTodosPontosDeUsuario(usuario);
		pontos.forEach(ponto -> tabelaDePontos.put(ponto, 
				                          armazenamento.recuperarPontosDeUsuario(usuario, ponto)));
		return tabelaDePontos;
	}
	
	protected Map<String, Integer> rankingDePonto(Ponto ponto){
		HashMap<String, Integer> ranking = new HashMap<String, Integer>();
		List<String> usuariosComPontos = armazenamento.getUsuariosComPontos();
		for (String usuario : usuariosComPontos) {
			List<Ponto> pontosDeUsuario = armazenamento.getTodosPontosDeUsuario(usuario);
			for (Ponto pontoDeUsuario : pontosDeUsuario) {
				if(pontoDeUsuario.equals(ponto))
					ranking.put(usuario, armazenamento.recuperarPontosDeUsuario(usuario, pontoDeUsuario));
			}
		}
		 Map<String, Integer> result = new LinkedHashMap<>();
		    Stream<Map.Entry<String, Integer>> st = ranking.entrySet().stream();
		    st.sorted( Map.Entry.comparingByValue(Comparator.reverseOrder()) )
		        .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

		return result;
		
	}
}
