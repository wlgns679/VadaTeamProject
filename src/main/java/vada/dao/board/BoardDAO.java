package vada.dao.board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import vada.service.board.BoardService;


public interface BoardDAO extends BoardService {
	
	public abstract Connection getConnection();
	
	public abstract void closeConnection(Connection conn);
	
	public abstract void closeConnection(Statement stmt, Connection conn);
	
	public abstract void closeConnection(ResultSet rs, Statement stmt);

	public abstract void closeConnection(ResultSet rs, Statement stmt, Connection conn);
	
}
