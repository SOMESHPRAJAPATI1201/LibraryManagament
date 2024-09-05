package services;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.ReserveBooksDAO;
import dto.BookDTO;
import dto.ReserveBooksDTO;

public class ReserveBookServices {

	private ReserveBooksDAO reserveBookDAO;
	private BookServices bookService;

	public ReserveBookServices(ReserveBooksDAO reserveBookDAO,BookServices bookService) {
		this.reserveBookDAO = reserveBookDAO;
		this.bookService = bookService;
	}

	public ArrayList<ReserveBooksDTO> getReserveBooksData(int reserveBookId) {
		return reserveBookDAO.getReserveBooksData(reserveBookId);
	}
	
	public ArrayList<ReserveBooksDTO> getReserveBooksData(int studentId, int bookID) {
		return reserveBookDAO.getSingleReserveBooksData(studentId, bookID);
	}

	public boolean ReserveBookEntry(ReserveBooksDTO issuebook, BookDTO bookdto) {
			if (reserveBookDAO.reserveBookEntry(issuebook)) {
				return true;
			}else {
				System.out.println("Failed to reserve book.");
				return false;
			}
	}

	public boolean returnReserveBookEntry(int id, BookDTO bookdto) {
		if(reserveBookDAO.deleteReserveBookEntry(id)>0) {
			bookService.editBookQuantity(bookdto.getId(), bookdto.getQuantity()+1);
			return true;
		}else {
			return false;
		}
	}
	
	public ReserveBooksDTO getReserveBookDataByIssuedBookId(int issuedBookId) {
		return reserveBookDAO.getReserveBookDataByIssuedBookId(issuedBookId);
	}
	
	public ArrayList<ReserveBooksDTO> getAdminReserveViewBooksData(int BookId) {
		return reserveBookDAO.getReservedViewBooksDataByBookID(BookId);
	}
	
	public boolean renewReserveByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = reserveBookDAO.renewReserveByBookId(issuedBookId, returnDate, issueDate);
		System.out.println(a);
		if (a>0) {
			return true;
		}else {
			return false;
		}	
	}
	
}
