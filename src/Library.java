import java.util.ArrayList;
import java.util.List;

/*
Requirement:
library system
function: people can search, borrow, renew books
rules: how many books for people can borrow
how long can people keep one book
how many times for people renew book
what is the fee of overdue book

core class:
library
book
member

main field:
library:books, members

main method:
Boolean searchBook(Library, Book book)
BookRecord borrow book(member, book) -> search book throw exception -> search member is available throw exception -> create borrow book class-> end
Boolean return book(member, book) -> if this is a record -> is it overdue -> is it charge for fee -> end
Boolean renew book(book) -> Is it overdue -> charge for fee

 */
public class Library {
    List<Book> books;
    List<Member> members;

    public Library() {
        books = new ArrayList<Book>();
        members = new ArrayList<Member>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Book searchBook (Book book) throws Exception{
        // TODO: should search title or author
        for (Book availableBook : books) {
            if (availableBook.equals(book) && availableBook.isAvailable) {
                return availableBook;
            }
        }
        throw new Exception("Not found");
    }

    public BorrowRecord borrow(Member member, Book book) throws Exception{
        if(book.isAvailable) {
            if(member.isAvailable()) {
                BorrowRecord record = new BorrowRecord(book, member, 0);
                member.borrowRecords.add(record);
            }

        }
        throw new Exception();
    }

    public BorrowRecord returnBook(Member member, Book book) throws Exception {
        try {
            BorrowRecord b = member.findRecord(book);
            if (b.isValid()) {

            }

        }catch (Exception e) {
            throw e;
        }

    }
    // ...

}

class Book {
    public int ISBN;
    public int id;
    private String name;
    private String author;
    public Boolean isAvailable;

    public Book(int ISBN, int id, String name, String author) {
        this.ISBN = ISBN;
        this.id = id;
        this.name = name;
        this.author = author;
        isAvailable = true;
    }

}

class Member {
    public int id;
    private String name;
    public List<BorrowRecord> borrowRecords;
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Boolean isAvailable() {
        // TODO: add rules
        return true;
    }

    public BorrowRecord findRecord(Book book) throws Exception{
        // TODO:
        throw new Exception();
    }

}

class BorrowRecord {
    private Book book;
    private Member member;
    private int startTime;
    private int endTime;
    private int expirationTime;
    public BorrowRecord(Book book, Member member, int time) {
        this.book = book;
        this.member = member;
        startTime = time;
    }

    public Boolean isValid() throws Exception {
        // TODO
        throw new Exception();
    }
}
