package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dao.IssueBooksDAO;
import dto.BookDTO;
import dto.IssueBooksDTO;
import static utills.ServicesHelper.*;

public class IssueBookServices {

	private IssueBooksDAO issuebookDAO;
	private BookServices bookservice;

	public IssueBookServices(IssueBooksDAO issuebookDAO, BookServices bookservice) {
		this.issuebookDAO = issuebookDAO;
		this.bookservice = bookservice;
	}

	public ArrayList<IssueBooksDTO> getIssuedBooksData(int id) {
		return issuebookDAO.getIssuedBooksData(id);
	}

	public ArrayList<IssueBooksDTO> getIssuedBooksData(int stdId, int bookID) {
		return issuebookDAO.getSingleIssueBooksData(stdId, bookID);
	}

	public int getDateValidation(LocalDate issuedDate, LocalDate returnDate, int bookId) {
		int a = BOOLEAN_VALIDATION_FALSE;
		List<IssueBooksDTO> list = issuebookDAO.getAllEntries().stream().filter(x -> x.getBook_id() == bookId).collect(Collectors.toList());
		List<LocalDate> dateList = null;
		for (IssueBooksDTO issuebook : list) {
			LocalDate issueStart = issuebook.getIssued_date();
			LocalDate issueEnd = issuebook.getReturn_date().plusDays(1);
			dateList = issueStart.datesUntil(issueEnd).collect(Collectors.toList());
			if (!(dateList.contains(issuedDate) || dateList.contains(returnDate))) {
				a = issuebook.getIssued_book_id();
			}
		}
		return a;
	}

	public int getDateValidationByIssuerID(int issuedId, LocalDate issuedDate, LocalDate returnDate) {
		int a = BOOLEAN_VALIDATION_FALSE;
		List<IssueBooksDTO> list = issuebookDAO.getAllEntries().stream().filter(x -> x.getIssued_id() == issuedId).collect(Collectors.toList());
		List<LocalDate> dateList = null;
		for (IssueBooksDTO issuebook : list) {
			LocalDate issueStart = issuebook.getIssued_date();
			LocalDate issueEnd = issuebook.getReturn_date().plusDays(1);
			dateList = issueStart.datesUntil(issueEnd).collect(Collectors.toList());
			if (!(dateList.contains(issuedDate) || dateList.contains(returnDate))) {
				a = issuebook.getIssued_book_id();
			}
		}
		return a;
	}

	public boolean issueBookEntry(IssueBooksDTO issuebook, BookDTO bookdto) {
		if (issuebookDAO.issueBookEntry(issuebook)) {
			bookservice.editBookQuantity(issuebook.getBook_id(), bookdto.getQuantity() - 1);
			return true;
		} else {
			System.out.println("Failed to issue book.");
			return false;
		}
	}

	public boolean issueSameBookEntry(IssueBooksDTO issuebook, BookDTO bookdto) {
		if (issuebookDAO.issueSameBookEntry(issuebook)) {
			return true;
		} else {
			System.out.println("Failed to issue book.");
			return false;
		}
	}

	public boolean returnIssuedBookEntry(int id, BookDTO bookdto) {
		if (issuebookDAO.deleteIssuedBookEntry(id) >= ROWS_AFFECTED) {
			bookservice.editBookQuantity(bookdto.getId(), bookdto.getQuantity() + 1);
			return true;
		} else {
			return false;
		}
	}

	public IssueBooksDTO getIssuedBookDataByIssuedBookId(int issuedBookId) {
		return issuebookDAO.getIssuedBookDataByIssuedBookId(issuedBookId);
	}

	public List<IssueBooksDTO> getIssuedViewBooksDataByBookID(int BookId) {
		return issuebookDAO.getIssuedViewBooksDataByBookID(BookId);
	}

	public boolean renewIssuedByBookId(int issuedBookId, LocalDate issueDate, LocalDate returnDate) {
		int a = issuebookDAO.renewIssuedByBookId(issuedBookId, issueDate, returnDate);
		System.out.println(a);
		if (a > BOOLEAN_VALIDATION_FALSE) {
			return true;
		} else {
			return false;
		}
	}

}
