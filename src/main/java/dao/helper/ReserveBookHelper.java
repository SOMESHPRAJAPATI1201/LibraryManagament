package dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ReserveBooksDTO;

public class ReserveBookHelper {
	
	public static ArrayList<ReserveBooksDTO> getReservedBooksDataDTO(ArrayList<ReserveBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		ReserveBooksDTO reserveBookDto = null;
		while (resultSet.next()) {
			reserveBookDto = new ReserveBooksDTO();
			reserveBookDto.setIssued_book_id(resultSet.getInt("id"));
			reserveBookDto.setBook_id(resultSet.getInt("book_id"));
			reserveBookDto.setBookname(resultSet.getString("name"));
			reserveBookDto.setAuthor(resultSet.getString("author"));
			reserveBookDto.setEdition(resultSet.getString("edition"));
			reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
			list.add(reserveBookDto);
		}
		return list;
	}
	
	public static ArrayList<ReserveBooksDTO> getReservedViewBooksDataByBookIdDTO(ArrayList<ReserveBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		ReserveBooksDTO reserveBookDto = null;
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
		return list;
	}
	
	public static ArrayList<ReserveBooksDTO> getAllReservedBookEntriesDTO(ArrayList<ReserveBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		ReserveBooksDTO reserveBookDto = null;
		while (resultSet.next()) {
			reserveBookDto = new ReserveBooksDTO();
			reserveBookDto.setBook_id(resultSet.getInt("book_id"));
			reserveBookDto.setStudentname(resultSet.getString("student_name"));
			reserveBookDto.setBookname(resultSet.getString("book_name"));
			reserveBookDto.setAuthor(resultSet.getString("author"));
			reserveBookDto.setEdition(resultSet.getString("edition"));
			reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
			list.add(reserveBookDto);
		}
		return list;
	}
	
	public static ArrayList<ReserveBooksDTO> getSingleReservedBooksDataDTO(ArrayList<ReserveBooksDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		ReserveBooksDTO reserveBookDto;
		if (resultSet != null) {
			while (resultSet.next()) {
				reserveBookDto = new ReserveBooksDTO();
				reserveBookDto.setBook_id(resultSet.getInt("book_id"));
				reserveBookDto.setStudent_id(resultSet.getInt("student_id"));
				list.add(reserveBookDto);
			}
		}
		return list;
	}
	
	public static boolean reservedBookEntryDTO(ReserveBooksDTO issuebook,PreparedStatement preparedStatement, boolean isSuccess) throws SQLException {
		java.sql.Date issuedDate = java.sql.Date.valueOf(issuebook.getIssued_date());
		java.sql.Date returnDate = java.sql.Date.valueOf(issuebook.getReturn_date());
		preparedStatement.setInt(1, issuebook.getBook_id());
		preparedStatement.setInt(2, issuebook.getStudent_id());
		preparedStatement.setDate(3, issuedDate);
		preparedStatement.setDate(4, returnDate);
		int rowsAffected = preparedStatement.executeUpdate();
		return isSuccess = rowsAffected > 0;
	}
	
	public static ReserveBooksDTO getReservedBookDataByIssuedBookIdDTO(ResultSet resultSet, ReserveBooksDTO reserveBookDto) throws SQLException {
		if (resultSet != null) {
			while (resultSet.next()) {
				reserveBookDto = new ReserveBooksDTO();
				reserveBookDto.setBook_id(resultSet.getInt("book_id"));
				reserveBookDto.setStudent_id(resultSet.getInt("student_id"));
				reserveBookDto.setReturn_date(resultSet.getDate("return_date").toLocalDate());
				reserveBookDto.setIssued_date(resultSet.getDate("issued_date").toLocalDate());
			}
		}
		return reserveBookDto;
	}

}
