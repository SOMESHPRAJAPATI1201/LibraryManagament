package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.IssueBooksDTO;
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
			preparedStatement = connection.prepareStatement("select books_data.id, books.id as book_id,  books.name, books.author, books.edition, books_data.issued_date, books_data.return_date from books_data inner join student on student.id = books_data.student_id inner join books on books.id = books_data.book_id where student.id = ?;");
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				issueBookDto = new IssueBooksDTO();
				issueBookDto.setIssued_book_id(resultSet.getInt("id"));
				issueBookDto.setBook_id(resultSet.getInt("book_id"));
				issueBookDto.setBookname(resultSet.getString("name"));
				issueBookDto.setAuthor(resultSet.getString("author"));
				issueBookDto.setEdition(resultSet.getString("edition"));
				issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				list.add(issueBookDto);
			}
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
			preparedStatement = connection.prepareStatement("select student.name as student_name, books.name as book_name, books.author, books.edition, books_data.issued_date, books_data.return_date from student inner join books_data on books_data.student_id = student.id inner join books on books.id = books_data.book_id where books.id = ?;");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				issueBookDto = new IssueBooksDTO();
				issueBookDto.setStudentname(resultSet.getString("student_name"));
				issueBookDto.setBookname(resultSet.getString("book_name"));
				issueBookDto.setAuthor(resultSet.getString("author"));
				issueBookDto.setEdition(resultSet.getString("edition"));
				issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				list.add(issueBookDto);
			}
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
					"select books.id as book_id, student.name as student_name, books.name as book_name, books.author, books.edition, books_data.issued_date, books_data.return_date from student inner join books_data on books_data.student_id = student.id inner join books on books.id = books_data.book_id;");
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				issueBookDto = new IssueBooksDTO();
				issueBookDto.setBook_id(resultSet.getInt("book_id"));
				issueBookDto.setStudentname(resultSet.getString("student_name"));
				issueBookDto.setBookname(resultSet.getString("book_name"));
				issueBookDto.setAuthor(resultSet.getString("author"));
				issueBookDto.setEdition(resultSet.getString("edition"));
				issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				list.add(issueBookDto);
			}
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
			list = new ArrayList<>();
			if (resultSet != null) {
				while (resultSet.next()) {
					issueBookDto = new IssueBooksDTO();
					issueBookDto.setBook_id(resultSet.getInt("book_id"));
					issueBookDto.setStudent_id(resultSet.getInt("student_id"));
					list.add(issueBookDto);
				}
			}
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
			java.sql.Date issuedDate = java.sql.Date.valueOf(issuebook.getIssued_date());
			java.sql.Date returnDate = java.sql.Date.valueOf(issuebook.getReturn_date());
			preparedStatement.setInt(1, issuebook.getBook_id());
			preparedStatement.setInt(2, issuebook.getStudent_id());
			preparedStatement.setDate(3, issuedDate);
			preparedStatement.setDate(4, returnDate);
			int rowsAffected = preparedStatement.executeUpdate();
			isSuccess = rowsAffected > 0;
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
			if (resultSet != null) {
				while (resultSet.next()) {
					issueBookDto = new IssueBooksDTO();
					issueBookDto.setBook_id(resultSet.getInt("book_id"));
					issueBookDto.setStudent_id(resultSet.getInt("student_id"));
					issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
					issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return issueBookDto;
	}

	public int renewIssuedByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE BOOKS_DATA SET return_date = ?, issued_date = ? WHERE id = ?;");
			preparedStatement.setDate(1, Date.valueOf(returnDate));
			preparedStatement.setDate(2, Date.valueOf(issueDate));
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
