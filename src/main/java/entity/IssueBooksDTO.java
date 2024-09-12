package entity;

import java.time.LocalDate;

public class IssueBooksDTO {

	private int book_id;
	private int issued_book_id;
	private int student_id;
	private LocalDate issued_date;
	private LocalDate return_date;
	private String bookname;
	private String author;
	private String edition;
	private int quantity;
	private String studentname;
	private String email;
	private int issued_id;

	public int getIssued_id() {
		return issued_id;
	}

	public void setIssued_id(int issued_id) {
		this.issued_id = issued_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getIssued_book_id() {
		return issued_book_id;
	}

	public void setIssued_book_id(int issued_book_id) {
		this.issued_book_id = issued_book_id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public LocalDate getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(LocalDate issued_date) {
		this.issued_date = issued_date;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}
}
