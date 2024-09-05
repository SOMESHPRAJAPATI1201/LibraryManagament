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

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	private BookDTO bookDto;
	private Generics utills;

	public BookDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<BookDTO> getAllBooks() {
		ArrayList<BookDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS;");
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				bookDto = new BookDTO();
				bookDto.setId(resultSet.getInt("id"));
				bookDto.setName(resultSet.getString("name"));
				bookDto.setAuthor(resultSet.getString("author"));
				bookDto.setQuantity(resultSet.getInt("quantity"));
				bookDto.setEdition(resultSet.getString("edition"));
				list.add(bookDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}

	public BookDTO getBook(int id) {
		bookDto = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?;");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bookDto = new BookDTO();
				bookDto.setId(resultSet.getInt("id"));
				bookDto.setName(resultSet.getString("name"));
				bookDto.setAuthor(resultSet.getString("author"));
				bookDto.setQuantity(resultSet.getInt("quantity"));
				bookDto.setEdition(resultSet.getString("edition"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return bookDto;
	}

	public List<BookDTO> checkBookAvailiblity(String name, String edition, String author, int quantity) {
		List<BookDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM BOOKS WHERE NAME = ? AND EDITION = ? AND AUTHOR = ? AND QUANTITY = ? ;");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, edition);
			preparedStatement.setString(3, author);
			preparedStatement.setInt(4, quantity);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				bookDto = new BookDTO();
				bookDto.setId(resultSet.getInt("id"));
				bookDto.setName(resultSet.getString("name"));
				bookDto.setAuthor(resultSet.getString("author"));
				bookDto.setQuantity(resultSet.getInt("quantity"));
				bookDto.setEdition(resultSet.getString("edition"));
				list.add(bookDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}

	public int AddBook(BookDTO bookdto) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO BOOKS (name, author, edition, quantity) values (?,?,?,?);");
			preparedStatement.setString(1, bookdto.getName());
			preparedStatement.setString(2, bookdto.getAuthor());
			preparedStatement.setString(3, bookdto.getEdition());
			preparedStatement.setInt(4, bookdto.getQuantity());
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}

	public int EditBook(BookDTO bookdto) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE BOOKS SET name = ?, author= ?, edition = ?, quantity = ? where id = ?;");
			preparedStatement.setString(1, bookdto.getName());
			preparedStatement.setString(2, bookdto.getAuthor());
			preparedStatement.setString(3, bookdto.getEdition());
			preparedStatement.setInt(4, bookdto.getQuantity());
			preparedStatement.setInt(5, bookdto.getId());
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}

	public int editBookQuantity(int id, int quantity) {
		int rowsAffected = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			String sql = "UPDATE books SET quantity = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, id);
			rowsAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return rowsAffected;
	}

	public int deletBook(int id) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM BOOKS WHERE ID = ?;");
			preparedStatement.setInt(1, id);
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}
}
