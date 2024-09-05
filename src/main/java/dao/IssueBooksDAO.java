package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import dto.IssueBooksDTO;
import utills.Generics;

public class IssueBooksDAO {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Generics utills;
	private Connection conn = null;
	private IssueBooksDTO issuebookdto;

	public IssueBooksDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<IssueBooksDTO> getIssuedBooksData(int studentId) {
		ArrayList<IssueBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select books_data.id, books.id as book_id,  books.name, books.author, books.edition, books_data.issued_date, books_data.return_date from books_data inner join student on student.id = books_data.student_id inner join books on books.id = books_data.book_id where student.id = ?;");
			pstmt.setInt(1, studentId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				issuebookdto = new IssueBooksDTO();
				issuebookdto.setIssued_book_id(rs.getInt("id"));
				issuebookdto.setBook_id(rs.getInt("book_id"));
				issuebookdto.setBookname(rs.getString("name"));
				issuebookdto.setAuthor(rs.getString("author"));
				issuebookdto.setEdition(rs.getString("edition"));
				issuebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				issuebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
				list.add(issuebookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<IssueBooksDTO> getAdminIssuedViewBooksData(int bookId) {
		ArrayList<IssueBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select student.name as student_name, books.name as book_name, books.author, books.edition, books_data.issued_date, books_data.return_date from student inner join books_data on books_data.student_id = student.id inner join books on books.id = books_data.book_id where books.id = ?;");
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				issuebookdto = new IssueBooksDTO();
				issuebookdto.setStudentname(rs.getString("student_name"));
				issuebookdto.setBookname(rs.getString("book_name"));
				issuebookdto.setAuthor(rs.getString("author"));
				issuebookdto.setEdition(rs.getString("edition"));
				issuebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				issuebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
				list.add(issuebookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<IssueBooksDTO> getAllEntries() {
		ArrayList<IssueBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select books.id as book_id, student.name as student_name, books.name as book_name, books.author, books.edition, books_data.issued_date, books_data.return_date from student inner join books_data on books_data.student_id = student.id inner join books on books.id = books_data.book_id;");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				issuebookdto = new IssueBooksDTO();
				issuebookdto.setBook_id(rs.getInt("book_id"));
				issuebookdto.setStudentname(rs.getString("student_name"));
				issuebookdto.setBookname(rs.getString("book_name"));
				issuebookdto.setAuthor(rs.getString("author"));
				issuebookdto.setEdition(rs.getString("edition"));
				issuebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				issuebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
				list.add(issuebookdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<IssueBooksDTO> getSingleIssueBooksData(int studentId, int bookId) {
		ArrayList<IssueBooksDTO> list = new ArrayList<>();
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("select book_id, student_id from books_data where student_id = ? and book_id = ?;");
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, bookId);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					issuebookdto = new IssueBooksDTO();
					issuebookdto.setBook_id(rs.getInt("book_id"));
					issuebookdto.setStudent_id(rs.getInt("student_id"));
					list.add(issuebookdto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public boolean issueBookEntry(IssueBooksDTO issuebook) {
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = utills.getConnection();
			String sql = "INSERT INTO BOOKS_DATA (book_id, student_id, issued_date, return_date) VALUES (?, ?, ?, ?)";
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

	public int deleteIssuedBookEntry(int issuedBookId) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM BOOKS_DATA WHERE ID = ?;");
			pstmt.setInt(1, issuedBookId);
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}
	
	public IssueBooksDTO getIssuedBookDataByIssuedBookId(int issuedBookId) {
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOOKS_DATA WHERE ID = ?;");
			pstmt.setInt(1, issuedBookId);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					issuebookdto = new IssueBooksDTO();
					issuebookdto.setBook_id(rs.getInt("book_id"));
					issuebookdto.setStudent_id(rs.getInt("student_id"));
					issuebookdto.setReturn_date(rs.getDate("return_date").toLocalDate());
					issuebookdto.setIssued_date(rs.getDate("issued_date").toLocalDate());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return issuebookdto;
	}
	
	public int renewIssuedByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("UPDATE BOOKS_DATA SET return_date = ?, issued_date = ? WHERE id = ?;");
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
