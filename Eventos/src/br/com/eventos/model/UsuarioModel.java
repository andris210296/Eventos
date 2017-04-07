package br.com.eventos.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.com.eventos.Controle;
import br.com.eventos.model.bd.EventoDAO;
import br.com.eventos.model.bd.EventoJDBC;
import br.com.eventos.model.bd.UsuarioDAO;
import br.com.eventos.model.bd.UsuarioJDBC;

public class UsuarioModel {
	
	private Controle controle;
	private Usuario usuario;	
	private UsuarioDAO uDAO;
	
	
	private List<Usuario> usuarios;
	
	
	
	public UsuarioModel(Controle controle){
		this.controle = controle;
		
		uDAO = new UsuarioJDBC();
		usuario = new Usuario();
		usuarios = new ArrayList<>();
		atualizarListaUsuarios();
		
		
	}
	
	// Método que loga o usuário e registra na memória suas informações
	public boolean logar(String login, String senha) throws Exception {
		atualizarListaUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().matches(login) && usuario.getSenha().matches(senha)) {
				setUsuario(usuario);
				controle.setUsuario(usuario);
				return true;
			}
		}
		return false;

	}

	// Método que verifica se já existe um usuário com este login
	public boolean loginIsUnico(String u) {
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().matches(u)) {
				return true;
			}
		}
		return false;
	}

	// Método de cadastrar usuário
	public void cadastrar(Usuario u) {
		try {
			uDAO.inserir(u);
			atualizarListaUsuarios();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void alterar(Usuario u) {
		try {
			uDAO.alterar(u);
			atualizarListaUsuarios();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deletar(Usuario u){
		try {
			uDAO.deletar(u);
			atualizarListaUsuarios();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void atualizarListaUsuarios() {
		usuarios.clear();
		try {
			List<Usuario> u = uDAO.listar();
			if (!u.isEmpty()) {
				for (Usuario usuario : u) {
					usuarios.add(usuario);
				}

			} else
				usuarios.clear();
			setUsuarios(usuarios);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro com BD!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	// Método que registra dados em uma JList
	public DefaultListModel<Usuario> listaJListUsuarios() {

		DefaultListModel<Usuario> listModel = new DefaultListModel();
		for (Usuario usuario : usuarios) {
			listModel.addElement(usuario);
		}
		return listModel;

	}
	

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(UsuarioDAO uDAO) {
		this.uDAO = uDAO;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	
	

}
