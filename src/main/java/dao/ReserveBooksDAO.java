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

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Generics utills;
	private Connection conn = null;
	private ReserveBooksDTO reservebookdto;

	public ReserveBooksDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<ReserveBooksDTO> getReserveBooksData(int studentId) {
		ArrayList<ReserveBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select books_data.id, books.name, books.author, books.edition, reserve_books_data.issued_date, reserve_books_data.return_date from reserve_books_data inner join student on student.id = reserve_books_data.student_id inner join books on books.id = reserve_books_data.book_id where student.id = ?;");
			pstmt.setInt(1, studentId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reservebookdto = new ReserveBooksDTO();
				reservebookdto.setIssued_book_id(rs.getInt("id"));
				reservebookdto.setBookname(rs.getString("name"));
				reservebookdto.setAuthor(rs.getString("author"));
				reservebookdto.setEdition(rs.getString("edition"));
				reservebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				reservebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
				list.add(reservebookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<ReserveBooksDTO> getAdminReservedViewBooksData(int bookId) {
		ArrayList<ReserveBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select student.name as student_name, books.name as book_name, books.author, books.edition, issued_date, return_date from student inner join reserve_books_data on reserve_books_data.student_id = student.id inner join books on books.id = reserve_books_data.book_id where books.id = ?;");
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reservebookdto = new ReserveBooksDTO();
				reservebookdto.setStudentname(rs.getString("student_name"));
				reservebookdto.setBookname(rs.getString("book_name"));
				reservebookdto.setAuthor(rs.getString("author"));
				reservebookdto.setEdition(rs.getString("edition"));
				reservebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				reservebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
				list.add(reservebookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<ReserveBooksDTO> getSingleReserveBooksData(int studentId, int bookId) {
		ArrayList<ReserveBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select book_id, student_id from reserve_books_data where student_id = ? and book_id = ?;");
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, bookId);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					reservebookdto = new ReserveBooksDTO();
					reservebookdto.setBook_id(rs.getInt("book_id"));
					reservebookdto.setStudent_id(rs.getInt("student_id"));
					list.add(reservebookdto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public boolean reserveBookEntry(ReserveBooksDTO issuebook) {
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = utills.getConnection();
			String sql = "INSERT INTO reserve_books_data (book_id, student_id, issued_date, return_date) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			java.sql.Date issuedDate = java.sql.Date.valueOf(issuebook.getIssued_date());
			java.sql.Date returnDate = java.sql.Date.valueOf(issuebook.getReturn_date());
			pstmt.setInt(1, issuebook.getBook_id());
			pstmt.setInt(2, issuebook.getStudent_id());
			pstmt.setDate(3, issuedDate);
			pstmt.setDate(4, returnDate);
			int rowsAffected = pstmt.executeUpdate();
			isSuccess = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return isSuccess;
	}

	public int deleteReserveBookEntry(int issuedBookId) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM reserve_books_data WHERE ID = ?;");
			pstmt.setInt(1, issuedBookId);
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}
	
	public ReserveBooksDTO getReserveBookDataByIssuedBookId(int issuedBookId) {
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM reserve_books_data WHERE ID = ?;");
			pstmt.setInt(1, issuedBookId);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					reservebookdto = new ReserveBooksDTO();
					reservebookdto.setBook_id(rs.getInt("book_id"));
					reservebookdto.setStudent_id(rs.getInt("student_id"));
					reservebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
					reservebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return reservebookdto;
	}
	
	public int renewReserveByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("UPDATE reserve_books_data SET return_date = ?, issued_date = ? WHERE id = ?;");
			pstmt.setDate(1, Date.valueOf(returnDate));
			pstmt.setDate(2, Date.valueOf(issueDate));
			pstmt.setInt(3, issuedBookId);
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}

}
