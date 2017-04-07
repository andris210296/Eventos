package br.com.eventos.model.bd;

import java.sql.*;
import java.util.*;

import br.com.eventos.model.Evento;
import br.com.eventos.model.Usuario;

public class EventoJDBC implements EventoDAO {
	ConexaoMysql conectar;

	public EventoJDBC() {
		conectar = ConexaoMysql.getDbCon();

	}

	@Override
	public void inserir(Evento evento) throws SQLException {

		PreparedStatement prepStmt = conectar.query("insert into evento values(0,?,?,?)");

		prepStmt.setInt(1, evento.getCriadorId());
		prepStmt.setString(2, evento.getNome());

		java.util.Date utilDate = evento.getData();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		prepStmt.setDate(3, sqlDate);

		conectar.insert(prepStmt);

	}

	@Override
	public List listar() throws SQLException {

		List<Evento> eventos = new ArrayList<>();

		String sql = "select * from evento";
		PreparedStatement prepStmt = conectar.query(sql);

		ResultSet rs = conectar.select(prepStmt, sql);

		if (rs != null)
			while (rs.next()) {

				Evento e = new Evento();

				e.setId(rs.getInt("id"));
				e.setCriador(e.setCriador_from_id(rs.getInt("criador")));
				e.setNome((rs.getString("nome")));

				java.sql.Date sqlDate = rs.getDate("data");
				java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
				e.setData(utilDate);

				eventos.add(e);
			}
		else
			eventos.add(new Evento());
		return eventos;
	}

	@Override
	public void deletar(Evento e) throws SQLException {

		PreparedStatement prepStmt = conectar.query("delete from evento where id=?");
		prepStmt.setInt(1, (int) e.getId());
		conectar.insert(prepStmt);

	}

	@Override
	public void alterar(Evento evento) throws SQLException {

		PreparedStatement prepStmt = conectar.query("update evento set nome = ?, data=? where id=?");
		
		prepStmt.setString(1, evento.getNome());

		java.util.Date utilDate = evento.getData();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		prepStmt.setDate(2, sqlDate);

		prepStmt.setInt(3, (int) evento.getId());

		conectar.insert(prepStmt);

	}

	// Método que cadastra um usuário e um evento na tabela usuario_evento, 
	// ou seja, indica que o usuário está participando do evento
	@Override
	public void participar(Usuario u, Evento e) throws SQLException {
		PreparedStatement prepStmt = conectar.query("insert into usuario_evento values(?,?)");

		prepStmt.setInt(1, (int) u.getId());
		prepStmt.setInt(2, e.getId());

		conectar.insert(prepStmt);

	}

	// Método que retorna a lista de participantes do evento
	@Override
	public List participantes(Evento evento) throws SQLException {

		List<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT e.id as id_evento, u.id as id_participante FROM usuario u"
				+ " INNER JOIN usuario_evento ue ON ue.id_usuario = u.id"
				+ " INNER JOIN evento e ON ue.id_evento = e.id order by e.id ";
		PreparedStatement prepStmt = conectar.query(sql);

		ResultSet rs = conectar.select(prepStmt, sql);

		if (rs != null)
			while (rs.next()) {
				if (rs.getInt("id_evento") == evento.getId()) {

					Usuario u = new Usuario();
					u = u.getUsuario_from_Id(rs.getInt("id_participante"));

					usuarios.add(u);
				}
			}
		else
			usuarios.add(new Usuario());
		return usuarios;

	}

	// Método que deleta o usuário e o evento da tabela usuario_evento,
	// ou seja, faz com que o usuário deixe de participar do evento
	@Override
	public void sair(Usuario u, Evento e) throws SQLException {

		PreparedStatement prepStmt = conectar.query("delete from usuario_evento where id_usuario=? and id_evento=?");
		prepStmt.setInt(1, (int) u.getId());
		prepStmt.setInt(2, e.getId());
		conectar.insert(prepStmt);
		
	}

}
