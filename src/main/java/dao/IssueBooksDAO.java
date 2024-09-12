package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dao.helper.IssueBookHelper;
import entity.IssueBooksDTO;
import utills.Generics;

public class IssueBooksDAO {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Generics utills;
	private Connection connection = null;
	private IssueBooksDTO issueBookDto = null;

	public IssueBooksDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<IssueBooksDTO> getIssuedBooksData(int studentId) {
		ArrayList<IssueBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("select books_data.id, books.id as book_id,  books.name, books.author, books.edition, books_data.issued_date, books_data.return_date from books_data inner join users_table on users_table.id = books_data.student_id inner join books on books.id = books_data.book_id where users_table.id = ? && role = 'student';");
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			list = IssueBookHelper.getIssuedBooksDataDTO(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	

	public List<IssueBooksDTO> getIssuedViewBooksDataByBookID(int bookId) {
		List<IssueBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("select users_table.name as student_name, books.name as book_name, books.author, books.edition, books.quantity, books_data.issued_date, books_data.return_date from users_table inner join books_data on books_data.student_id = users_table.id inner join books on books.id = books_data.book_id where books.id = ? && users_table.role = 'student';");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			list = IssueBookHelper.getIssuedViewBooksDataByBookIdDTO(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	

	public ArrayList<IssueBooksDTO> getAllEntries() {
		ArrayList<IssueBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement(
					"select books_data.id as issued_books_id, books.id as book_id, books.quantity , users_table.name as student_name, books.name as book_name, books.author, books.edition, books_data.issued_date, books_data.return_date , books_data.issued_id from users_table inner join books_data on books_data.student_id = users_table.id inner join books on books.id = books_data.book_id;");
			resultSet = preparedStatement.executeQuery();
			list = IssueBookHelper.getAllEntriesDTO(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	

	public ArrayList<IssueBooksDTO> getSingleIssueBooksData(int studentId, int bookId) {
		ArrayList<IssueBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement(
					"select book_id, student_id from books_data where student_id = ? and book_id = ?;");
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, bookId);
			resultSet = preparedStatement.executeQuery();
			list = IssueBookHelper.getSingleIssueBooksDataDTO(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	

	public boolean issueBookEntry(IssueBooksDTO issuebook) {
		boolean isSuccess = false;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			String sql = "INSERT INTO BOOKS_DATA (book_id, student_id, issued_date, return_date) VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			isSuccess = IssueBookHelper.issueBookEntryDTO(issuebook, preparedStatement, isSuccess);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return isSuccess;
	}
	
	public boolean issueSameBookEntry(IssueBooksDTO issuebook) {
		boolean isSuccess = false;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			String sql = "INSERT INTO BOOKS_DATA (book_id, student_id, issued_date, return_date, issued_id) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			isSuccess = IssueBookHelper.issueSameBookEntryDTO(issuebook, preparedStatement, isSuccess);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return isSuccess;
	}
	
	

	public int deleteIssuedBookEntry(int issuedBookId) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM BOOKS_DATA WHERE ID = ?;");
			preparedStatement.setInt(1, issuedBookId);
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}
	

	public IssueBooksDTO getIssuedBookDataByIssuedBookId(int issuedBookId) {
		issueBookDto = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS_DATA WHERE ID = ?;");
			preparedStatement.setInt(1, issuedBookId);
			resultSet = preparedStatement.executeQuery();
			issueBookDto = IssueBookHelper.getIssuedBookDataByIssuedBookIdDTO(resultSet, issueBookDto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return issueBookDto;
	}
	
	

	public int renewIssuedByBookId(int issuedBookId, LocalDate issueDate, LocalDate returnDate) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE BOOKS_DATA SET issued_date = ?, return_date = ? WHERE id = ?;");
			preparedStatement.setDate(1, Date.valueOf(issueDate));
			preparedStatement.setDate(2, Date.valueOf(returnDate));
			preparedStatement.setInt(3, issuedBookId);
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}

}
