package br.com.eventos.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

import br.com.eventos.model.bd.UsuarioDAO;
import br.com.eventos.model.bd.UsuarioJDBC;

public class Evento {

	private int id;
	private String nome;
	private Usuario criador;
	private Date data;
	private List<Usuario> usuarios;

	public Evento() {
		// TODO Auto-generated constructor stub
	}

	public Evento(int id, String nome, Usuario criador, Date data, List<Usuario> usuarios) {
		this.id = id;
		this.nome = nome;
		this.criador = criador;
		this.data = data;
		usuarios = new ArrayList<>();
		this.usuarios = usuarios;

	}

	// Método que retorna o id do usuário que criou o evento
	public int getCriadorId() {
		return (int) getCriador().getId();
	}

	
	//Método que retorna um objeto Usuário de acordo com o id pedido
	public Usuario setCriador_from_id(int id) {

		try {
			UsuarioDAO uDAO = new UsuarioJDBC();
			return uDAO.getUsuario(id);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao recuperar Criador por Id", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Usuario u = new Usuario();
			return u;
		}
	}
	
	

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date data = getData();
		String sDate = "Data: " + dateFormat.format(data);

		return "  " + getNome() + "      " + sDate;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
