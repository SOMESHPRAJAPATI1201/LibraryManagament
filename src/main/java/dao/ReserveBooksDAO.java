package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dto.ReserveBooksDTO;
import utills.Generics;

public class ReserveBooksDAO {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Generics utills;
	private Connection connection = null;
	private ReserveBooksDTO reserveBookDto;

	public ReserveBooksDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<ReserveBooksDTO> getReserveBooksData(int studentId) {
		ArrayList<ReserveBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("select books_data.id, books.name, books.author, books.edition, reserve_books_data.issued_date, reserve_books_data.return_date from reserve_books_data inner join student on student.id = reserve_books_data.student_id inner join books on books.id = reserve_books_data.book_id where student.id = ?;");
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				reserveBookDto = new ReserveBooksDTO();
				reserveBookDto.setIssued_book_id(resultSet.getInt("id"));
				reserveBookDto.setBookname(resultSet.getString("name"));
				reserveBookDto.setAuthor(resultSet.getString("author"));
				reserveBookDto.setEdition(resultSet.getString("edition"));
				reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				list.add(reserveBookDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	public ArrayList<ReserveBooksDTO> getReservedViewBooksDataByBookID(int bookId) {
		ArrayList<ReserveBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("select student.name as student_name, books.name as book_name, books.author, books.edition, issued_date, return_date from student inner join reserve_books_data on reserve_books_data.student_id = student.id inner join books on books.id = reserve_books_data.book_id where books.id = ?;");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				reserveBookDto = new ReserveBooksDTO();
				reserveBookDto.setStudentname(resultSet.getString("student_name"));
				reserveBookDto.setBookname(resultSet.getString("book_name"));
				reserveBookDto.setAuthor(resultSet.getString("author"));
				reserveBookDto.setEdition(resultSet.getString("edition"));
				reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				list.add(reserveBookDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}

	public ArrayList<ReserveBooksDTO> getSingleReserveBooksData(int studentId, int bookId) {
		ArrayList<ReserveBooksDTO> list = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("select book_id, student_id from reserve_books_data where student_id = ? and book_id = ?;");
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, bookId);
			resultSet = preparedStatement.executeQuery();
			list = new ArrayList<>();
			if (resultSet!=null) {
				while (resultSet.next()) {
					reserveBookDto = new ReserveBooksDTO();
					reserveBookDto.setBook_id(resultSet.getInt("book_id"));
					reserveBookDto.setStudent_id(resultSet.getInt("student_id"));
					list.add(reserveBookDto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return list;
	}

	public boolean reserveBookEntry(ReserveBooksDTO issuebook) {
		boolean isSuccess = false;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			String sql = "INSERT INTO reserve_books_data (book_id, student_id, issued_date, return_date) VALUES (?, ?, ?, ?)";
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

	public int deleteReserveBookEntry(int issuedBookId) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM reserve_books_data WHERE ID = ?;");
			preparedStatement.setInt(1, issuedBookId);
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}
	
	public ReserveBooksDTO getReserveBookDataByIssuedBookId(int issuedBookId) {
		reserveBookDto = null;
		connection = null;
		preparedStatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM reserve_books_data WHERE ID = ?;");
			preparedStatement.setInt(1, issuedBookId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet!=null) {
				while (resultSet.next()) {
					reserveBookDto = new ReserveBooksDTO();
					reserveBookDto.setBook_id(resultSet.getInt("book_id"));
					reserveBookDto.setStudent_id(resultSet.getInt("student_id"));
					reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
					reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return reserveBookDto;
	}
	
	public int renewReserveByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = 0;
		connection = null;
		preparedStatement = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE reserve_books_data SET return_date = ?, issued_date = ? WHERE id = ?;");
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
