package br.com.eventos;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.com.eventos.model.Evento;
import br.com.eventos.model.EventoModel;
import br.com.eventos.model.Usuario;
import br.com.eventos.model.UsuarioModel;
import br.com.eventos.model.bd.EventoDAO;
import br.com.eventos.model.bd.EventoJDBC;
import br.com.eventos.model.bd.UsuarioDAO;
import br.com.eventos.model.bd.UsuarioJDBC;
import br.com.eventos.view.CadastroView;
import br.com.eventos.view.LoginView;
import br.com.eventos.view.MenuView;

public class Controle {

	private UsuarioModel usuarioM;
	private EventoModel eventoM;
	private Usuario usuario;
	private Evento evento;
	

	private LoginView loginV;
	private MenuView menuV;
	private CadastroView cadastroV;



	public Controle() throws Exception {

		usuario = new Usuario();
		evento = new Evento();
		
		usuarioM = new UsuarioModel(this);
		eventoM = new EventoModel(this);
				
		loginV = new LoginView(usuarioM,this);
		menuV = new MenuView(usuarioM,eventoM,this);
		cadastroV = new CadastroView(usuarioM,this);

	}

	// Método que exibe a tela de Login e esconde as outras telas
	public void JanelaLogin() {
		loginV.setVisible(true);
		loginV.setLocation(200, 200);
		loginV.setSize(300, 150);

		menuV.setVisible(false);
		cadastroV.setVisible(false);

	}

	// Método que exibe a tela de Menu e esconde as outras telas
	public void JanelaMenu() {
		menuV.set();
		menuV.setVisible(true);

		loginV.setVisible(false);
		cadastroV.setVisible(false);
		cadastroV.dispose();
	}

	// Método que exibe a tela de Cadastro e esconde as outras telas
	public void JanelaCadastro() {
		cadastroV.setVisible(true);
		cadastroV.setLocation(200, 200);
		cadastroV.setSize(300, 150);

		loginV.setVisible(false);
		menuV.setVisible(false);

	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public LoginView getLoginV() {
		return loginV;
	}

	public void setLoginV(LoginView loginV) {
		this.loginV = loginV;
	}

	public MenuView getMenuV() {
		return menuV;
	}

	public void setMenuV(MenuView menuV) {
		this.menuV = menuV;
	}



	public CadastroView getCadastroV() {
		return cadastroV;
	}

	public void setCadastroV(CadastroView cadastroV) {
		this.cadastroV = cadastroV;
	}


}
