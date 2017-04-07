package br.com.eventos.model.bd;

import java.sql.*;
import java.util.*;

import br.com.eventos.model.Usuario;

public class UsuarioJDBC implements UsuarioDAO {
	ConexaoMysql conectar;

	public UsuarioJDBC() {
		conectar = ConexaoMysql.getDbCon();
	}

	@Override
	public void inserir(Usuario usuario) throws SQLException {
		PreparedStatement prepStmt = conectar.query("insert into usuario values(0,?,?,?)");

		prepStmt.setString(1, usuario.getLogin());
		prepStmt.setString(2, usuario.getSenha());
		prepStmt.setString(3, usuario.getNome());

		conectar.insert(prepStmt);
	}

	@Override
	public List listar() throws SQLException {

		List<Usuario> usuarios = new ArrayList<>();

		String sql = "select * from Usuario";
		PreparedStatement prepStmt = conectar.query(sql);

		ResultSet rs = conectar.select(prepStmt, sql);

		while (rs.next()) {
			Usuario u = new Usuario();
			u.setId(rs.getInt("id"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setNome(rs.getString("nome"));
			usuarios.add(u);
		}
		return usuarios;
	}

	@Override
	public void deletar(Usuario usuario) throws SQLException {		
		PreparedStatement prepStmt = conectar.query("delete from usuario where id=?");
		prepStmt.setInt(1, (int) usuario.getId());
		conectar.insert(prepStmt);
	}

	@Override
	public void alterar(Usuario usuario) throws SQLException {

		PreparedStatement prepStmt = conectar.query("update usuario set login = ?, senha=?, nome =? where id=?");
		// PreparedStatement prepStmt = conectar.query("insert into usuario values(0,?,?,?)");

		prepStmt.setString(1, usuario.getLogin());
		prepStmt.setString(2, usuario.getSenha());
		prepStmt.setString(3, usuario.getNome());
		prepStmt.setInt(4, (int) usuario.getId());

		conectar.insert(prepStmt);

	}
	
	
	// Método que retorna um objeto Usuário de acordo com o id passado
	@Override
	public Usuario getUsuario(int id) throws SQLException {
		String sql = "select * from Usuario where id = " + id;
		PreparedStatement prepStmt = conectar.query(sql);

		ResultSet rs = conectar.select(prepStmt, sql);
		Usuario u = new Usuario();
		while (rs.next()) {
			u.setId(rs.getInt("id"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setNome(rs.getString("nome"));
		}
		return u;
	}
}
