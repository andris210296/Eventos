package br.com.eventos;

public class Iniciar {
	
	public static void main(String[] args) {
		// Método main que inicia todo o projeto
		
		Controle controle;
		try {
			controle = new Controle();
			controle.JanelaLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
