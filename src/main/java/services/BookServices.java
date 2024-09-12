package services;

import java.util.ArrayList;
import java.util.List;
import dao.BookDAO;
import entity.BookDTO;

import static utills.ServicesHelper.*;

public class BookServices {

	private BookDAO bookDAO;

	public BookServices(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public ArrayList<BookDTO> fetchAllBooks() {
		return bookDAO.getAllBooks();
	}

	public boolean addBook(BookDTO bookDTO) {
		if (checkBookAvailaiblity(bookDTO.getName(), bookDTO.getEdition(), bookDTO.getAuthor(),bookDTO.getQuantity()).size() == ARRAYLIST_SIZE_VALIDATION) {
				if (bookDAO.AddBook(bookDTO) >= ROWS_AFFECTED) {
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
	
	public boolean editBook(BookDTO bookDTO) {
		if (checkBookAvailaiblity(bookDTO.getName(), bookDTO.getEdition(), bookDTO.getAuthor(), bookDTO.getQuantity()).size() == ARRAYLIST_SIZE_VALIDATION) {
			if (bookDAO.EditBook(bookDTO) >= ROWS_AFFECTED) {
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

	public BookDTO getBook(int bookId) {
		return bookDAO.getBook(bookId);
	}
	
	public boolean editBookQuantity(int bookId, int quantity) {
		if (bookDAO.editBookQuantity(bookId, quantity) > QUANTITY_VALIDATION) {
			System.out.println("Quantity Edited");
			return true;
		}else {
			System.out.println("No Change");
			return false;
		}
	}
	
	public List<BookDTO> checkBookAvailaiblity(String bookName, String bookEdition, String bookAuthor,int bookQuantity) {
		return bookDAO.checkBookAvailiblity(bookName, bookEdition, bookAuthor, bookQuantity);
	}

	public boolean deletBook(int bookId) {
		if (bookDAO.deletBook(bookId) >= ROWS_AFFECTED) {
			System.out.println("Book Removed Sucessfully");
			return true;
		} else {
			System.out.println("Failed to delete book");
			return false;
		}
	}

}
