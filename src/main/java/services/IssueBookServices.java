package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dao.IssueBooksDAO;
import dto.BookDTO;
import dto.IssueBooksDTO;
import static utills.ServicesHelper.*;

public class IssueBookServices {

	private IssueBooksDAO issuebookDAO;
	private BookServices bookservice;

	public IssueBookServices(IssueBooksDAO issuebookDAO,BookServices bookservice) {
		this.issuebookDAO = issuebookDAO;
		this.bookservice = bookservice;
	}

	public ArrayList<IssueBooksDTO> getIssuedBooksData(int id) {
		return issuebookDAO.getIssuedBooksData(id);
	}
	
	public ArrayList<IssueBooksDTO> getIssuedBooksData(int stdId, int bookID) {
		return issuebookDAO.getSingleIssueBooksData(stdId, bookID);
	}

	public boolean issueBookEntry(IssueBooksDTO issuebook, BookDTO bookdto) {
		if (bookdto.getQuantity()>QUANTITY_VALIDATION) {
			if (issuebookDAO.issueBookEntry(issuebook)) {
				bookservice.editBookQuantity(issuebook.getBook_id(), bookdto.getQuantity()-1);
				return true;
			}else {
				System.out.println("Failed to issue book.");
				return false;
			}
		}else {
			System.out.println("Book is out of stock.");
			return false;
		}
	}

	public boolean returnIssuedBookEntry(int id, BookDTO bookdto) {
		if(issuebookDAO.deleteIssuedBookEntry(id)>=ROWS_AFFECTED) {
			bookservice.editBookQuantity(bookdto.getId(), bookdto.getQuantity()+1);
			return true;
		}else {
			return false;
		}
	}
	
	public IssueBooksDTO getIssuedBookDataByIssuedBookId(int issuedBookId) {
		return issuebookDAO.getIssuedBookDataByIssuedBookId(issuedBookId);
	}
	
	public List<IssueBooksDTO> getIssuedViewBooksDataByBookID(int BookId) {
		return issuebookDAO.getIssuedViewBooksDataByBookID(BookId);
	}
	
	public boolean renewIssuedByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = issuebookDAO.renewIssuedByBookId(issuedBookId, returnDate, issueDate);
		System.out.println(a);
		if (a>BOOLEAN_VALIDATION_FALSE) {
			return true;
		}else {
			return false;
		}	
	}
	
}
