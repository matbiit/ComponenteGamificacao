package br.ita;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Armazenamento {

	private String filename = "banco.txt";
	private static HashMap<String, HashMap<Ponto, Integer>> bancoDeDados = 
												new HashMap<String, HashMap<Ponto, Integer>>();
	
	public Armazenamento(){
		atualizarBanco();
	}
	
	private void salvarAlteracoes(){
		File file = new File(filename);
	    if(!file.exists()){
	    	try {
				file.createNewFile();
				OutputStream out = new FileOutputStream(file);
				ObjectOutputStream s = new ObjectOutputStream(out);
				s.writeObject(bancoDeDados);
				s.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void atualizarBanco(){
		try {
			InputStream in = new FileInputStream(filename);
			ObjectInputStream s = new ObjectInputStream(in);
			bancoDeDados = (HashMap<String, HashMap<Ponto, Integer>>) s.readObject();
			s.close();
			in.close();
		} catch (FileNotFoundException fnfe){
			System.out.println("Iniciando sistema!");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void salvarPontosDeUsuario(String usuario, Ponto ponto, int quantidade){
		HashMap<Ponto, Integer> tabelaDePontos = bancoDeDados.get(usuario);
		if(tabelaDePontos == null){
			tabelaDePontos = new HashMap<Ponto, Integer>();
			tabelaDePontos.put(ponto, new Integer(quantidade));
			bancoDeDados.put(usuario, tabelaDePontos);
		} else{
			tabelaDePontos.put(ponto, tabelaDePontos.getOrDefault(ponto, 0) + quantidade);
			bancoDeDados.put(usuario, tabelaDePontos);
		}
		salvarAlteracoes();
	}
	
	protected Integer recuperarPontosDeUsuario(String usuario, Ponto ponto) {
		HashMap<Ponto, Integer> tabelaDePontos = bancoDeDados.getOrDefault(usuario, null);
		if(tabelaDePontos == null)
			return 0;
		else
			return tabelaDePontos.getOrDefault(ponto, 0);
	} 
	
	protected  List<String> getUsuariosComPontos(){
		return new ArrayList<String>(bancoDeDados.keySet());
	}
	
	protected List<Ponto> getTodosPontosDeUsuario(String usuario){
		return new ArrayList<Ponto>(bancoDeDados.getOrDefault(usuario, null).keySet());
	}
	
	
}
