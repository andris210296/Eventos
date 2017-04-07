package br.com.eventos.model.bd;

import java.sql.*;
import java.util.List;

import br.com.eventos.model.Usuario;

public interface UsuarioDAO {
	public void inserir(Usuario usuario)throws SQLException;	

	public void deletar(Usuario usuario) throws SQLException;

	public void alterar(Usuario usuario)throws SQLException;

	public List listar()throws SQLException;
	
	public Usuario getUsuario(int id) throws SQLException;;

}
