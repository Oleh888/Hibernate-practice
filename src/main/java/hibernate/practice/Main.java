package hibernate.practice;

import hibernate.practice.lib.Injector;
import hibernate.practice.model.Author;
import hibernate.practice.model.Book;
import hibernate.practice.model.Genre;
import hibernate.practice.service.AuthorService;
import hibernate.practice.service.BookService;
import hibernate.practice.service.GenreService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("hibernate.practice");

    public static void main(String[] args) {
        BookService bookService =
                (BookService) INJECTOR.getInstance(BookService.class);
        Book book = new Book();
        book.setTitle("Kobzar");
        bookService.add(book);
        Book book1 = new Book();
        book1.setTitle("Misto");
        bookService.add(book1);
        String rezByTitle1 = bookService.getBookByTitle("Kobzar").toString();
        String rezByTitle2 = bookService.getBookByTitle("Misto").toString();
        Genre genre = new Genre();
        Genre genre1 = new Genre();
        genre.setNameOfGenre("novel");
        genre1.setNameOfGenre("novel");
        genre.setBook(book);
        genre1.setBook(book1);
        GenreService genreService =
                (GenreService) INJECTOR.getInstance(GenreService.class);
        genreService.add(genre);
        genreService.add(genre1);
        List<Book> getByGenre = genreService.getAllBooksByGenre(genre);
        Author author = new Author();
        author.setFullName("Ivan Franko");
        author.setBook(new ArrayList<>());
        author.getBook().add(book);
        author.getBook().add(book1);
        AuthorService authorService =
                (AuthorService) INJECTOR.getInstance(AuthorService.class);
        authorService.add(author);
        List<Book> getByAuthor = authorService.getListOfBooksByAuthor(author);
        // System.out.println("Result of method getBookByTitle Kobzar: " + rezByTitle1);
        // System.out.println("Result of method getBookByTitle Misto: " + rezByTitle2);
        // System.out.println("Result of method getAllBooksByGenre novel: " + getByGenre);
        // System.out.println("Result of method getAllBooksByAuthor: Ivan Franko" + getByAuthor);
    }
}
