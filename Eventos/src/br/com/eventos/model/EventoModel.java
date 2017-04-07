package br.com.eventos.model;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.com.eventos.Controle;
import br.com.eventos.model.bd.EventoDAO;
import br.com.eventos.model.bd.EventoJDBC;

public class EventoModel {

	private Controle controle;
	private Evento evento;
	private EventoDAO eDAO;

	private List<Evento> eventos;

	public EventoModel(Controle controle) {
		this.controle = controle;

		eDAO = new EventoJDBC();
		evento = new Evento();
		eventos = new ArrayList<>();
		atualizarListaEventos();

	}

	// Método para cadastrar um novo evento
	public void cadastrarEvento(Evento e) {
		try {
			eDAO.inserir(e);
			atualizarListaEventos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	// Método para alterar um evento
	public void alterar(Evento e) {
		try {
			eDAO.alterar(e);
			atualizarListaEventos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Método para deletar um novo evento
	public void deletar(Evento e) {
		try {
			eDAO.deletar(e);
			atualizarListaEventos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	// Método que registra um usuário em um evento, indicando que ele está participando
	public void participar(Usuario u, Evento e) {
		try {
			eDAO.participar(u, e);
			atualizarListaEventos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	// Método que remove o usuário de um evento
	public void sair(Usuario u, Evento e) {
		try {
			eDAO.sair(u, e);
			atualizarListaEventos();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	// Método que atualiza a lista eventos
	public void atualizarListaEventos() {
		eventos.clear();
		try {
			List<Evento> e = eDAO.listar();
			if (!e.isEmpty()) {
				for (Evento evento : e) {
					atualizarListaParticipantes(evento);
					eventos.add(evento);
				}

			} else
				eventos.clear();
			setEventos(eventos);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro com BD!", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void atualizarListaParticipantes(Evento e) {
		if (e.getUsuarios() != null)
			e.getUsuarios().clear();
		try {
			List<Usuario> participantes = eDAO.participantes(e);
			if (!participantes.isEmpty()) {
				e.setUsuarios(participantes);

			} else
				participantes.clear();
			e.setUsuarios(participantes);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro com BD!", "Erro", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}

	}

	public boolean podeParticipar(Usuario u, Evento e) {
		for (Evento evento : getEventos()) {
			if (evento == e) {
				if (evento.getCriador().getId() == u.getId())
					return false;
				for (Usuario us : evento.getUsuarios()) {
					if (us.getId() == u.getId())
						return false;
				}

			}
		}
		return true;
	}

	// Método que preenche os elementos de uma JList
	public DefaultListModel<Evento> listaJListCriadorEventos() {

		DefaultListModel<Evento> listModel = new DefaultListModel();

		for (Evento evento : eventos) {
			if (evento.getCriador().getId() == controle.getUsuario().getId()) {
				listModel.addElement(evento);
			}
		}
		return listModel;

	}

	public DefaultListModel<Evento> listaJListEventos() {
		DefaultListModel<Evento> listModel = new DefaultListModel();
		for (Evento evento : eventos) {
			listModel.addElement(evento);
		}
		return listModel;

	}

	public DefaultListModel<Usuario> listaJListParticipantes(Evento evento) {

		DefaultListModel<Usuario> listModel = new DefaultListModel();

		for (Evento e : eventos) {
			if (e == evento) {
				listModel.addElement(e.getCriador());
				if (evento.getUsuarios() != null) {
					for (Usuario usuario : evento.getUsuarios()) {
						listModel.addElement(usuario);
					}
				}
			}
		}
		return listModel;

	}

	public DefaultListModel<Evento> listajListParticipandoEventos(Usuario u) {

		DefaultListModel<Evento> listModel = new DefaultListModel();

		for (Evento evento : eventos) {
			for (Usuario usuario : evento.getUsuarios()) {
				if (usuario.getId() == u.getId())
					listModel.addElement(evento);
			}

		}
		return listModel;

	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public EventoDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(EventoDAO eDAO) {
		this.eDAO = eDAO;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
