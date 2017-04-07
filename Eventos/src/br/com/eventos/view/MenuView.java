package br.com.eventos.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.eventos.Controle;
import br.com.eventos.model.Evento;
import br.com.eventos.model.EventoModel;
import br.com.eventos.model.Usuario;
import br.com.eventos.model.UsuarioModel;

import java.awt.event.*;
import java.util.Date;
import java.util.Vector;
import java.text.*;

public class MenuView extends JFrame implements ListSelectionListener {

	private JPanel contentPane;

	private Controle controle;
	private Usuario usuario;
	private UsuarioModel usuarioM;

	private EventoModel eventoM;
	private Evento evento;

	private JList jListCriadorEventos;
	private JList jListUsuarios;
	private JList jListEventos;
	private JList jListParticipandoEventos;
	private JList jListParticipantes;

	private JLabel lblParticipantes;

	String[] listB = { "Some content on the right panel", "More content", "Some more content", "More and more content",
			"More and more content", "More and more content", "More and more content" };

	private JTextField txtNomeEvento;
	private JTextField txtDataEvento;

	private JLabel lblUs;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JTextField txtNovoNomeEvento;
	private JTextField txtNovaData;

	public MenuView(UsuarioModel usuarioM, EventoModel eventoM, Controle controle) {
		this.usuarioM = usuarioM;
		this.eventoM = eventoM;
		this.controle = controle;
		setTitle("Menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		JPanel pnlUsuario = new JPanel();
		tabbedPane.addTab("Meu Perfil", null, pnlUsuario, null);
		pnlUsuario.setLayout(new BorderLayout(0, 0));

		lblUs = new JLabel("Olá teste, seja bem-vindo(a) novamente!");
		lblUs.setHorizontalAlignment(SwingConstants.CENTER);
		lblUs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlUsuario.add(lblUs, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		pnlUsuario.add(panel_1, BorderLayout.CENTER);
		GridLayout gl_panel_1 = new GridLayout(6, 2);
		gl_panel_1.setVgap(30);
		gl_panel_1.setHgap(15);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel_3 = new JLabel("Novo nome:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_3);

		txtNome = new JTextField();
		txtNome.setText("");
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Novo Login:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);

		txtLogin = new JTextField();
		txtLogin.setText("");
		panel_1.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nova Senha:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_5);

		txtSenha = new JPasswordField();
		txtSenha.setText("");
		panel_1.add(txtSenha);

		JButton btnAlterar = new JButton("Alterar Dados");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Usuario u = new Usuario();
					u.setId(usuario.getId());
					u.setNome(getTxtNome().getText());
					u.setLogin(getTxtLogin().getText());
					u.setSenha(getTxtSenha().getText());

					// Antes da alteração acontecer, o comando abaixo verificar se o login é único 
					// ou se pertence ao próprio usuário logado
					if (!usuarioM.loginIsUnico(u.getLogin()) || usuario.getLogin().matches(getTxtLogin().getText())) {
						usuarioM.getUsuarios().add(u);
						usuarioM.alterar(u);
						controle.JanelaLogin();
					} else
						JOptionPane.showMessageDialog(null, "Este login já existe!", "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Algum campo foi digitado incorretamente", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton btnDeletar = new JButton("Deletar Conta");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int reply = JOptionPane.showConfirmDialog(null, "Você têm certeza que deseja deletar sua conta?",
						"Deletar Conta", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					usuarioM.deletar(usuario);
					controle.JanelaLogin();
				}

			}
		});
		panel_1.add(btnDeletar);
		panel_1.add(btnAlterar);

		JButton btnLogout = new JButton("Sair");
		panel_1.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controle.setUsuario(new Usuario());
				controle.JanelaLogin();

			}
		});

		JPanel pnlUEventos = new JPanel();
		tabbedPane.addTab("Meus Eventos", null, pnlUEventos, null);
		pnlUEventos.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane1 = new JSplitPane();
		pnlUEventos.add(splitPane1, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setToolTipText("");
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane1.setLeftComponent(splitPane_1);

		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("Eventos criados por mim");
		splitPane_1.setLeftComponent(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_10 = new JLabel("Meus Eventos");
		lblNewLabel_10.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_10);

		jListCriadorEventos = new JList(listB);
		jListCriadorEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListCriadorEventos.addListSelectionListener(this);
		panel_3.add(jListCriadorEventos);

		JPanel panel_4 = new JPanel();
		panel_4.setToolTipText("Eventos que estarei participando");
		splitPane_1.setRightComponent(panel_4);
		panel_4.setLayout(new GridLayout(2, 1, 0, 30));

		JLabel lblNewLabel_11 = new JLabel("Eventos que vou");
		lblNewLabel_11.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_11);

		jListParticipandoEventos = new JList(listB);
		jListParticipandoEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListParticipandoEventos.addListSelectionListener(this);
		panel_4.add(jListParticipandoEventos);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane1.setRightComponent(splitPane_2);

		JPanel panel = new JPanel();
		splitPane_2.setLeftComponent(panel);
		GridLayout gl_panel = new GridLayout(3, 2);
		gl_panel.setVgap(20);
		gl_panel.setHgap(2);
		panel.setLayout(gl_panel);

		JLabel lblNewLabel = new JLabel("Novo Evento:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);

		txtNomeEvento = new JTextField();
		panel.add(txtNomeEvento);
		txtNomeEvento.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_1);

		txtDataEvento = new JTextField();
		panel.add(txtDataEvento);
		txtDataEvento.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);

		JButton btnNovoEvento = new JButton("Criar Evento");
		btnNovoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					evento = new Evento();
					evento.setNome(getTxtNomeEvento().getText());
					evento.setCriador(getUsuario());

					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date data = (java.util.Date) formatter.parse(getTxtDataEvento().getText());
					evento.setData(data);

					eventoM.getEventos().add(evento);
					eventoM.cadastrarEvento(evento);

					set();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao criar Evento!", "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel.add(btnNovoEvento);

		JPanel panel_2 = new JPanel();
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new GridLayout(4, 2, 10, 30));

		JLabel lblNewLabel_6 = new JLabel("Alterar Evento");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_7 = new JLabel("Novo Nome:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_7);

		txtNovoNomeEvento = new JTextField();
		txtNovoNomeEvento.setText("");
		panel_2.add(txtNovoNomeEvento);
		txtNovoNomeEvento.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Nova Data:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel_8);

		txtNovaData = new JTextField();
		panel_2.add(txtNovaData);
		txtNovaData.setColumns(10);

		JButton btnDeletarEvento = new JButton("Deletar Evento");
		btnDeletarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (evento != null) {
					int reply = JOptionPane.showConfirmDialog(null,
							"Você têm certeza que deseja deletar o evento: " + evento.getNome() + "?", "Deletar Evento",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						eventoM.deletar(evento);
						limparCampos();
						set();

					}
				}

			}
		});
		panel_2.add(btnDeletarEvento);

		JButton btnAlterarEvento = new JButton("Alterar Evento");
		btnAlterarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (evento != null) {
					try {
						Evento e = new Evento();
						e.setId(evento.getId());
						e.setNome(getTxtNovoNomeEvento().getText());

						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date data = (java.util.Date) formatter.parse(getTxtNovaData().getText());
						e.setData(data);

						eventoM.getEventos().add(e);
						eventoM.alterar(e);
						limparCampos();
						set();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Algum campo foi digitado incorretamente", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		});
		panel_2.add(btnAlterarEvento);

		JPanel pnlUE = new JPanel();
		tabbedPane.addTab("Usu\u00E1rios e Eventos", null, pnlUE, null);
		pnlUE.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		pnlUE.add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_3 = new JSplitPane();
		splitPane.setRightComponent(splitPane_3);

		JPanel panel_6 = new JPanel();
		splitPane_3.setLeftComponent(panel_6);
		panel_6.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_13 = new JLabel("Eventos");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setVerticalAlignment(SwingConstants.TOP);
		panel_6.add(lblNewLabel_13);

		jListEventos = new JList(listB);
		panel_6.add(jListEventos);
		jListEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListEventos.addListSelectionListener(this);

		JPanel panel_7 = new JPanel();
		splitPane_3.setRightComponent(panel_7);
		panel_7.setLayout(new GridLayout(2, 1, 0, 0));

		lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblParticipantes.setVerticalAlignment(SwingConstants.TOP);
		panel_7.add(lblParticipantes);

		jListParticipantes = new JList(listB);
		panel_7.add(jListParticipantes);

		JPanel panel_5 = new JPanel();
		splitPane.setLeftComponent(panel_5);
		panel_5.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_12 = new JLabel("Usu\u00E1rios");
		lblNewLabel_12.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_12);

		jListUsuarios = new JList(listB);
		panel_5.add(jListUsuarios);
		jListUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnVoltar = new JButton("Voltar");

	}

	// Método que seta todas as informações, tanto d usuário como dos eventos
	public void set() {
		jListParticipantes.setModel(new DefaultListModel());
		usuario = usuarioM.getUsuario();

		jListCriadorEventos.setModel(eventoM.listaJListCriadorEventos());
		jListUsuarios.setModel(usuarioM.listaJListUsuarios());
		jListEventos.setModel(eventoM.listaJListEventos());
		jListParticipandoEventos.setModel(eventoM.listajListParticipandoEventos(usuario));
		

		getLblUs().setText("Olá " + usuario.getNome() + ", seja bem-vindo(a) novamente!");

		getTxtNome().setText(usuario.getNome());
		getTxtLogin().setText(usuario.getLogin());
		getTxtSenha().setText(usuario.getSenha());

	}

	// Conjunto de comandos que controlam quando algum item de alguma JList é selecionado
	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (e.getSource() == jListCriadorEventos) {

			// Quando o usuário seleciona algum item da JList jListCriadorEventos, 
			// estes comandos preenchem os campos de alteração
			evento = (Evento) jListCriadorEventos.getSelectedValue();
			if (evento != null) {
				getTxtNovoNomeEvento().setText(evento.getNome());

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date data = evento.getData();
				String sDate = dateFormat.format(data);
				getTxtNovaData().setText(sDate);
			}

		}

		if (e.getSource() == jListEventos) {

			// Quando o usuário seleciona algum item da JList jListEventos,
			// estes comandos preenchem a lista de participantes e pergunta se o usuário deseja participar
			// mas ele verifica antes se o usuário é o criador ou já está participando
			evento = (Evento) jListEventos.getSelectedValue();
			if (evento != null) {
				lblParticipantes.setText("Participantes do evento: " + evento.getNome());
				jListParticipantes.setModel(eventoM.listaJListParticipantes(evento));

				if (eventoM.podeParticipar(usuario, evento)) {
					int reply = JOptionPane.showConfirmDialog(null,
							"Você quer participar do evento: " + evento.getNome() + "?",
							"Participar de " + evento.getNome(), JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						eventoM.participar(usuario, evento);
						set();

					}
				}

			}

		}
		if (e.getSource() == jListParticipandoEventos) {

			// Quando o usuário seleciona algum item da JList jListParticipandoEventos,
			// pergunta se ele deseja sair do evento
			evento = (Evento) jListParticipandoEventos.getSelectedValue();
			if (evento != null) {
				int reply = JOptionPane.showConfirmDialog(null, "Você quer sair do evento: " + evento.getNome() + "?",
						"Sair do " + evento.getNome(), JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					eventoM.sair(usuario, evento);
					set();

				}

			}
		}

	}

	public void limparCampos() {
		getTxtNomeEvento().setText("");
		getTxtDataEvento().setText("");
		getTxtNovoNomeEvento().setText("");
		getTxtNovaData().setText("");
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

	public String[] getListB() {
		return listB;
	}

	public void setListB(String[] listB) {
		this.listB = listB;
	}

	public JTextField getTxtNomeEvento() {
		return txtNomeEvento;
	}

	public void setTxtNomeEvento(JTextField txtNomeEvento) {
		this.txtNomeEvento = txtNomeEvento;
	}

	public JTextField getTxtDataEvento() {
		return txtDataEvento;
	}

	public void setTxtDataEvento(JTextField txtDataEvento) {
		this.txtDataEvento = txtDataEvento;
	}

	public JLabel getLblUs() {
		return lblUs;
	}

	public void setLblUs(JLabel lblUs) {
		this.lblUs = lblUs;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTextField getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(JTextField txtLogin) {
		this.txtLogin = txtLogin;
	}

	public JPasswordField getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(JPasswordField txtSenha) {
		this.txtSenha = txtSenha;
	}

	public JTextField getTxtNovoNomeEvento() {
		return txtNovoNomeEvento;
	}

	public void setTxtNovoNomeEvento(JTextField txtNovoNomeEvento) {
		this.txtNovoNomeEvento = txtNovoNomeEvento;
	}

	public JTextField getTxtNovaData() {
		return txtNovaData;
	}

	public void setTxtNovaData(JTextField txtNovaData) {
		this.txtNovaData = txtNovaData;
	}

}
