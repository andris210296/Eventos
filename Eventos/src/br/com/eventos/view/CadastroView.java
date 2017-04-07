package br.com.eventos.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.com.eventos.Controle;
import br.com.eventos.model.Usuario;
import br.com.eventos.model.UsuarioModel;


public class CadastroView extends JFrame implements ActionListener{
	
		
	private JFrame janela;
	private JLabel lblVazio = new JLabel("");
	private JLabel lblLogin = new JLabel("Login:");
	private JTextField txtLogin = new JTextField();
	private JLabel lblSenha = new JLabel("Senha:");
	private JPasswordField txtSenha = new JPasswordField();
	private JLabel lblNome = new JLabel("Nome:");
	private JTextField txtNome = new JTextField();

	private JButton btnCadastrar = new JButton("Cadastrar");
	private JButton btnVoltar = new JButton("Voltar");
	
	private Controle controle;
	private UsuarioModel usuarioM;
	
	public CadastroView(UsuarioModel usuarioM,Controle controle) throws Exception{
		this.usuarioM = usuarioM;
		this.controle = controle;
		
		
		this.janela = new JFrame("Logar");
		this.janela.setSize(800,800);
		Container container = getContentPane();
		container.setLayout(new GridLayout(4, 2));

		
		container.add(getLblNome());container.add(getTxtNome());
		container.add(getLblLogin());container.add(getTxtLogin());
		container.add(getLblSenha());container.add(getTxtSenha());
		container.add(getBtnVoltar());container.add(getBtnCadastrar());
		
		
		this.janela.setDefaultCloseOperation(3);
		this.janela.pack();
		this.setFocusable(true);
		this.janela.setResizable(false);
		
		btnVoltar.addActionListener(this);
		btnCadastrar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadastrar) {
			
			try {
				Usuario usuario = new Usuario();
				usuario.setNome(getTxtNome().getText());
				usuario.setLogin(getTxtLogin().getText());
				usuario.setSenha(getTxtSenha().getText());
				
				// Verifica se o login do usuário é único
				if(!usuarioM.loginIsUnico(usuario.getLogin())){
					usuarioM.getUsuarios().add(usuario);
					usuarioM.cadastrar(usuario);
					controle.JanelaLogin();
				}
				else 
					JOptionPane.showMessageDialog(null,"Este login já existe!","Erro", JOptionPane.ERROR_MESSAGE);
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"Algum campo foi digitado incorretamente","Erro", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == btnVoltar) {
			controle.JanelaLogin();
		}
		
	}

	public JFrame getJanela() {
		return janela;
	}

	public void setJanela(JFrame janela) {
		this.janela = janela;
	}

	public JLabel getLblVazio() {
		return lblVazio;
	}

	public void setLblVazio(JLabel lblVazio) {
		this.lblVazio = lblVazio;
	}

	public JLabel getLblLogin() {
		return lblLogin;
	}

	public void setLblLogin(JLabel lblLogin) {
		this.lblLogin = lblLogin;
	}

	public JTextField getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(JTextField txtLogin) {
		this.txtLogin = txtLogin;
	}

	public JLabel getLblSenha() {
		return lblSenha;
	}

	public void setLblSenha(JLabel lblSenha) {
		this.lblSenha = lblSenha;
	}

	public JPasswordField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JPasswordField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public JLabel getLblNome() {
		return lblNome;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(JButton btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(JButton btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	public UsuarioModel getUsuarioM() {
		return usuarioM;
	}

	public void setUsuarioM(UsuarioModel usuarioM) {
		this.usuarioM = usuarioM;
	}
	
	
	

}
