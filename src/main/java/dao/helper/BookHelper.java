package dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BookDTO;

public class BookHelper {
	
	public static ArrayList<BookDTO> getAllBooksDTO(ArrayList<BookDTO> list, ResultSet resultSet, BookDTO bookDto) throws SQLException {
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
		return list;
	}
	
	public static BookDTO getBookDTO(ResultSet resultSet, BookDTO bookDto) throws SQLException {
		while (resultSet.next()) {
			bookDto = new BookDTO();
			bookDto.setId(resultSet.getInt("id"));
			bookDto.setName(resultSet.getString("name"));
			bookDto.setAuthor(resultSet.getString("author"));
			bookDto.setQuantity(resultSet.getInt("quantity"));
			bookDto.setEdition(resultSet.getString("edition"));
		}
		return bookDto;
	}
	
	public static List<BookDTO> checkBookAvailaiblityDTO(List<BookDTO> list, ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		BookDTO bookDto = null;
		while (resultSet.next()) {
			bookDto = new BookDTO();
			bookDto.setId(resultSet.getInt("id"));
			bookDto.setName(resultSet.getString("name"));
			bookDto.setAuthor(resultSet.getString("author"));
			bookDto.setQuantity(resultSet.getInt("quantity"));
			bookDto.setEdition(resultSet.getString("edition"));
			list.add(bookDto);
		}
		return list;
	}
	
	public static int AddBookDTO(PreparedStatement preparedStatement, BookDTO bookdto, int a) throws SQLException {
		preparedStatement.setString(1, bookdto.getName());
		preparedStatement.setString(2, bookdto.getAuthor());
		preparedStatement.setString(3, bookdto.getEdition());
		preparedStatement.setInt(4, bookdto.getQuantity());
		return a = preparedStatement.executeUpdate();
	}
	
	public static int EditBookDTO(PreparedStatement preparedStatement, int a, BookDTO bookdto) throws SQLException {
		preparedStatement.setString(1, bookdto.getName());
		preparedStatement.setString(2, bookdto.getAuthor());
		preparedStatement.setString(3, bookdto.getEdition());
		preparedStatement.setInt(4, bookdto.getQuantity());
		preparedStatement.setInt(5, bookdto.getId());
		return a = preparedStatement.executeUpdate();
	}

}
