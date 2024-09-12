package dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IssueBooksDTO;

public class IssueBookHelper {

	public static ArrayList<IssueBooksDTO> getIssuedBooksDataDTO(ArrayList<IssueBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		IssueBooksDTO issueBookDto = null;
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
		return list;
	}
	
	public static List<IssueBooksDTO> getIssuedViewBooksDataByBookIdDTO(List<IssueBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		IssueBooksDTO issueBookDto = null;
		while (resultSet.next()) {
			issueBookDto = new IssueBooksDTO();
			issueBookDto.setStudentname(resultSet.getString("student_name"));
			issueBookDto.setBookname(resultSet.getString("book_name"));
			issueBookDto.setAuthor(resultSet.getString("author"));
			issueBookDto.setEdition(resultSet.getString("edition"));
			issueBookDto.setQuantity(resultSet.getInt("quantity"));
			issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
			list.add(issueBookDto);
		}
		return list;
	}
	
	public static ArrayList<IssueBooksDTO> getAllEntriesDTO(ArrayList<IssueBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		IssueBooksDTO issueBookDto = null;
		while (resultSet.next()) {
			issueBookDto = new IssueBooksDTO();
			issueBookDto.setIssued_book_id(resultSet.getInt("issued_books_id"));
			issueBookDto.setBook_id(resultSet.getInt("book_id"));
			issueBookDto.setStudentname(resultSet.getString("student_name"));
			issueBookDto.setBookname(resultSet.getString("book_name"));
			issueBookDto.setAuthor(resultSet.getString("author"));
			issueBookDto.setEdition(resultSet.getString("edition"));
			issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
			issueBookDto.setIssued_id(resultSet.getInt("issued_id"));
			list.add(issueBookDto);
		}
		return list;
	}
	
	public static ArrayList<IssueBooksDTO> getSingleIssueBooksDataDTO(ArrayList<IssueBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		IssueBooksDTO issueBookDto;
		if (resultSet != null) {
			while (resultSet.next()) {
				issueBookDto = new IssueBooksDTO();
				issueBookDto.setBook_id(resultSet.getInt("book_id"));
				issueBookDto.setStudent_id(resultSet.getInt("student_id"));
				list.add(issueBookDto);
			}
		}
		return list;
	}
	
	public static boolean issueBookEntryDTO(IssueBooksDTO issuebook,PreparedStatement preparedStatement, boolean isSuccess) throws SQLException {
		java.sql.Date issuedDate = java.sql.Date.valueOf(issuebook.getIssued_date());
		java.sql.Date returnDate = java.sql.Date.valueOf(issuebook.getReturn_date());
		preparedStatement.setInt(1, issuebook.getBook_id());
		preparedStatement.setInt(2, issuebook.getStudent_id());
		preparedStatement.setDate(3, issuedDate);
		preparedStatement.setDate(4, returnDate);
		int rowsAffected = preparedStatement.executeUpdate();
		return isSuccess = rowsAffected > 0;
	}
	
	public static boolean issueSameBookEntryDTO(IssueBooksDTO issuebook,PreparedStatement preparedStatement, boolean isSuccess) throws SQLException {
		java.sql.Date issuedDate = java.sql.Date.valueOf(issuebook.getIssued_date());
		java.sql.Date returnDate = java.sql.Date.valueOf(issuebook.getReturn_date());
		preparedStatement.setInt(1, issuebook.getBook_id());
		preparedStatement.setInt(2, issuebook.getStudent_id());
		preparedStatement.setDate(3, issuedDate);
		preparedStatement.setDate(4, returnDate);
		preparedStatement.setInt(5, issuebook.getIssued_id());
		int rowsAffected = preparedStatement.executeUpdate();
		return isSuccess = rowsAffected > 0;
	}
	
	
	public static IssueBooksDTO getIssuedBookDataByIssuedBookIdDTO(ResultSet resultSet, IssueBooksDTO issueBookDto) throws SQLException {
		if (resultSet != null) {
			while (resultSet.next()) {
				issueBookDto = new IssueBooksDTO();
				issueBookDto.setBook_id(resultSet.getInt("book_id"));
				issueBookDto.setStudent_id(resultSet.getInt("student_id"));
				issueBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				issueBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			}
		}
		return issueBookDto;
	}
	
}
