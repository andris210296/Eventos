package br.com.eventos.model;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.eventos.model.bd.UsuarioDAO;
import br.com.eventos.model.bd.UsuarioJDBC;

public class Usuario {
	
	private long id;
	private String login;
	private String senha;
	private String nome;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Usuario(int id, String login,String senha, String nome){
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		
	}
	
	// Método que retorna um objeto Usuário de acordo com um id passado
	public Usuario getUsuario_from_Id(int id){
		try {
			UsuarioDAO uDAO = new UsuarioJDBC();
			return uDAO.getUsuario(id);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao recuperar Usuario por Id", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Usuario u = new Usuario();
			return u;
		}
	}
	
	@Override
	public String toString() {		
		return "  " + getNome() + "      " + getLogin();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
