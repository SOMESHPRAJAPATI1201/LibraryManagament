package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;
import utills.Generics;

public class BookDAO {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Generics utills;
	private Connection conn = null;
	private BookDTO bookdto;

	public BookDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<BookDTO> getAllBooks() {
		ArrayList<BookDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS;");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookdto = new BookDTO();
				bookdto.setId(rs.getInt("id"));
				bookdto.setName(rs.getString("name"));
				bookdto.setAuthor(rs.getString("author"));
				bookdto.setQuantity(rs.getInt("quantity"));
				bookdto.setEdition(rs.getString("edition"));
				list.add(bookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public BookDTO getBook(int id) {
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?;");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookdto = new BookDTO();
				bookdto.setId(rs.getInt("id"));
				bookdto.setName(rs.getString("name"));
				bookdto.setAuthor(rs.getString("author"));
				bookdto.setQuantity(rs.getInt("quantity"));
				bookdto.setEdition(rs.getString("edition"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return bookdto;
	}
	
	public List<BookDTO> checkBookAvailiblity(String name, String edition, String author, int quantity) {
		List<BookDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS WHERE NAME = ? AND EDITION = ? AND AUTHOR = ? AND QUANTITY = ? ;");
			pstmt.setString(1, name);
			pstmt.setString(2, edition);
			pstmt.setString(3, author);
			pstmt.setInt(4, quantity);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookdto = new BookDTO();
				bookdto.setId(rs.getInt("id"));
				bookdto.setName(rs.getString("name"));
				bookdto.setAuthor(rs.getString("author"));
				bookdto.setQuantity(rs.getInt("quantity"));
				bookdto.setEdition(rs.getString("edition"));
				list.add(bookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public List<BookDTO> checkBookAvailiblity(String name, String edition, String author) {
		List<BookDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS WHERE NAME = ? AND EDITION = ? AND AUTHOR = ?;");
			pstmt.setString(1, name);
			pstmt.setString(2, edition);
			pstmt.setString(3, author);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookdto = new BookDTO();
				bookdto.setId(rs.getInt("id"));
				bookdto.setName(rs.getString("name"));
				bookdto.setAuthor(rs.getString("author"));
				bookdto.setQuantity(rs.getInt("quantity"));
				bookdto.setEdition(rs.getString("edition"));
				list.add(bookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public int AddBook(BookDTO bookdto) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO BOOKS (name, author, edition, quantity) values (?,?,?,?);");
			pstmt.setString(1, bookdto.getName());
			pstmt.setString(2, bookdto.getAuthor());
			pstmt.setString(3, bookdto.getEdition());
			pstmt.setInt(4, bookdto.getQuantity());
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return a;
	}
	
	public int EditBook(BookDTO bookdto) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("UPDATE BOOKS SET name = ?, author= ?, edition = ?, quantity = ? where id = ?;");
			pstmt.setString(1, bookdto.getName());
			pstmt.setString(2, bookdto.getAuthor());
			pstmt.setString(3, bookdto.getEdition());
			pstmt.setInt(4, bookdto.getQuantity());
			pstmt.setInt(5, bookdto.getId());
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return a;
	}

	public int editBookQuantity(int id, int quantity) {
	    int rowsAffected = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = utills.getConnection();
	        String sql = "UPDATE books SET quantity = ? WHERE id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, quantity);
	        pstmt.setInt(2, id);
	        rowsAffected = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("SQL error: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        utills.closeConnection(conn, pstmt);
	    }
	    return rowsAffected;
	}

	public int deletBook(int id) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM BOOKS WHERE ID = ?;");
			pstmt.setInt(1, id);
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}
}
