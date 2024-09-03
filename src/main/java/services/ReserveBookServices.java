package services;

import java.time.LocalDate;
import java.util.ArrayList;
import dao.ReserveBooksDAO;
import dto.BookDTO;
import dto.ReserveBooksDTO;

public class ReserveBookServices {

	private ReserveBooksDAO reservebookDAO;
	private BookServices bookservice;

	public ReserveBookServices(ReserveBooksDAO reservebookDAO,BookServices bookservice) {
		this.reservebookDAO = reservebookDAO;
		this.bookservice = bookservice;
	}

	public ArrayList<ReserveBooksDTO> getReserveBooksData(int id) {
		return reservebookDAO.getReserveBooksData(id);
	}
	
	public ArrayList<ReserveBooksDTO> getReserveBooksData(int stdId, int bookID) {
		return reservebookDAO.getSingleReserveBooksData(stdId, bookID);
	}

	public boolean ReserveBookEntry(ReserveBooksDTO issuebook, BookDTO bookdto) {
			if (reservebookDAO.reserveBookEntry(issuebook)) {
				return true;
			}else {
				System.out.println("Failed to reserve book.");
				return false;
			}
	}

	public boolean returnReserveBookEntry(int id, BookDTO bookdto) {
		if(reservebookDAO.deleteReserveBookEntry(id)>0) {
			bookservice.editBookQuantity(bookdto.getId(), bookdto.getQuantity()+1);
			return true;
		}else {
			return false;
		}
	}
	
	public ReserveBooksDTO getReserveBookDataByIssuedBookId(int issuedBookId) {
		return reservebookDAO.getReserveBookDataByIssuedBookId(issuedBookId);
	}
	
	public ArrayList<ReserveBooksDTO> getAdminReserveViewBooksData(int BookId) {
		return reservebookDAO.getAdminReservedViewBooksData(BookId);
	}
	
	public boolean renewReserveByBookId(int issuedBookId, LocalDate returnDate, LocalDate issueDate ) {
		int a = reservebookDAO.renewReserveByBookId(issuedBookId, returnDate, issueDate);
		System.out.println(a);
		if (a>0) {
			return true;
		}else {
			return false;
		}	
	}
	
}
