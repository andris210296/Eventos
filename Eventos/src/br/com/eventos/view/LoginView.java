package br.com.eventos.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.com.eventos.Controle;
import br.com.eventos.model.UsuarioModel;

public class LoginView extends JFrame implements ActionListener {

	private JFrame janela;
	private JLabel lblVazio = new JLabel("");
	private JLabel lblLogin = new JLabel("Login:");
	private JTextField txtLogin = new JTextField();
	private JLabel lblSenha = new JLabel("Senha:");
	private JPasswordField txtSenha = new JPasswordField();

	private JButton btnLogar = new JButton("Logar");
	private JButton btnNovo = new JButton("Nova Conta");

	private Controle controle;
	
	private UsuarioModel usuarioM;

	public LoginView(UsuarioModel usuarioM, Controle controle) throws Exception {
		this.usuarioM = usuarioM;
		this.controle = controle;

		this.janela = new JFrame("Logar");
		this.janela.setSize(800, 800);

		Container container = getContentPane();
		container.setLayout(new GridLayout(3, 2));

		container.add(getLblLogin());
		container.add(getTxtLogin());
		container.add(getLblSenha());
		container.add(getTxtSenha());
		container.add(getBtnNovo());
		container.add(getBtnLogar());
				
		this.janela.setDefaultCloseOperation(3);
		this.janela.pack();
		this.setFocusable(true);
		this.janela.setResizable(false);
			
		btnLogar.addActionListener(this);
		btnNovo.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogar) {

			try {
				if (usuarioM.logar(getTxtLogin().getText(), getTxtSenha().getText()))
					controle.JanelaMenu();
				else
					throw new Exception();
					
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Não foi possível Logar!", "Erro",
						JOptionPane.ERROR_MESSAGE);				
			}
		}
		if (e.getSource() == btnNovo) {
			controle.JanelaCadastro();
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

	public JButton getBtnLogar() {
		return btnLogar;
	}

	public void setBtnLogar(JButton btnLogar) {
		this.btnLogar = btnLogar;
	}

	public JButton getBtnNovo() {
		return btnNovo;
	}

	public void setBtnNovo(JButton btnNovo) {
		this.btnNovo = btnNovo;
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public UsuarioModel getUsuarioM() {
		return usuarioM;
	}

	public void setUsuarioM(UsuarioModel usuarioM) {
		this.usuarioM = usuarioM;
	}
	

}
