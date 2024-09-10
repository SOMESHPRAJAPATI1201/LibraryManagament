package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.helper.ReserveBookHelper;
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
			preparedStatement = connection.prepareStatement("select books_data.id, books.name, books.author, books.edition, reserve_books_data.issued_date, reserve_books_data.return_date from reserve_books_data inner join users_table on users_table.id = reserve_books_data.student_id inner join books on books.id = reserve_books_data.book_id where users_table.id = ?;");
			preparedStatement.setInt(1, studentId);
			resultSet = preparedStatement.executeQuery();
			list = ReserveBookHelper.getReservedBooksDataDTO(list, resultSet);
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
			preparedStatement = connection.prepareStatement("select users_table.name as student_name, books.name as book_name, books.author, books.edition, issued_date, return_date from users_table inner join reserve_books_data on reserve_books_data.student_id = users_table.id inner join books on books.id = reserve_books_data.book_id where books.id = ?;");
			preparedStatement.setInt(1, bookId);
			resultSet = preparedStatement.executeQuery();
			list = ReserveBookHelper.getReservedViewBooksDataByBookIdDTO(list, resultSet);
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
			list = ReserveBookHelper.getSingleReservedBooksDataDTO(list, resultSet);
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
			isSuccess = ReserveBookHelper.reservedBookEntryDTO(issuebook, preparedStatement, isSuccess);
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
			reserveBookDto = ReserveBookHelper.getReservedBookDataByIssuedBookIdDTO(resultSet, reserveBookDto);
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
