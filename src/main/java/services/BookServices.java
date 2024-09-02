package services;

import java.util.ArrayList;
import java.util.List;
import dao.BookDAO;
import dto.BookDTO;

public class BookServices {

	private BookDAO bookDAO;

	public BookServices(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public ArrayList<BookDTO> fetchAllBooks() {
		bookDAO.getAllBooks().forEach(x -> System.out.println(x.getName() + "::" + x.getAuthor() + "::" + x.getId()
				+ "::" + x.getQuantity() + "::" + x.getEdition()));
		return bookDAO.getAllBooks();
	}

	public boolean addBook(BookDTO bookdto) {
		if (checkBookAvailaiblity(bookdto.getName(), bookdto.getEdition(), bookdto.getAuthor()).size() == 0) {
			if (bookDAO.AddBook(bookdto) >= 1) {
				System.out.println("Book Added Succesfully");
				return true;
			} else {
				System.err.println("Failed To Add Book");
				return false;
			}
		} else {
			System.err.println("Book Already Exists");
			return false;
		}
	}
	
	public boolean editBook(BookDTO bookdto) {
		if (checkBookAvailaiblity(bookdto.getName(), bookdto.getEdition(), bookdto.getAuthor()).size() == 0) {
			if (bookDAO.EditBook(bookdto) >= 1) {
				System.out.println("Book Edited Succesfully");
				return true;
			} else {
				System.err.println("Failed To Edit Book");
				return false;
			}
		} else {
			System.err.println("Book Already Exists");
			return false;
		}
	}

	public BookDTO getBook(int id) {
		return bookDAO.getBook(id);
	}
	
	public boolean editBookQuantity(int id, int quantity) {
		if (bookDAO.editBookQuantity(id, quantity) > 0) {
			System.out.println("Quantity Edited");
			return true;
		}else {
			System.out.println("No Change");
			return false;
		}
	}


	public List<BookDTO> checkBookAvailaiblity(String name, String edition, String author) {
		return bookDAO.checkBookAvailiblity(name, edition, author);
	}

	public boolean deletBook(int id) {
		if (bookDAO.deletBook(id) >= 1) {
			System.out.println("Book Removed Sucessfully");
			return true;
		} else {
			System.out.println("Failed to delete book");
			return false;
		}
	}

}
