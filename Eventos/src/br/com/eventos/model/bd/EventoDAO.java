package br.com.eventos.model.bd;

import java.sql.*;
import java.util.List;

import br.com.eventos.model.Evento;
import br.com.eventos.model.Usuario;

public interface EventoDAO {
	public void inserir(Evento evento)throws SQLException;	

	public void deletar(Evento e) throws SQLException;

	public void alterar(Evento evento)throws SQLException;

	public List listar()throws SQLException;
	
	public void participar(Usuario u, Evento e ) throws SQLException;
	
	public List participantes(Evento e)throws SQLException;
	
	public void sair(Usuario u, Evento e ) throws SQLException;

}
