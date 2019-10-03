package com.isil.practica2.service;

import com.isil.practica2.model.Author;
import com.isil.practica2.model.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService implements GenericService<Book, String> {
    List<Book> books = new ArrayList<>(
            Arrays.asList(
                    new Book("0000", new ArrayList<Author>(
                            Arrays.asList(new Author("71893721", "Juan", "Perez", "Sosa", 54, ""))
                    ), "Titulo1", "AAAAA", LocalDate.now(), 50, "Español"),
                    new Book("1111", new ArrayList<Author>(
                            Arrays.asList(new Author("09963820", "Maria", "Delgado", "Copare", 35, ""))
                    ), "Titulo2", "BBBBB", LocalDate.now(), 100, "Inglés"),
                    new Book("2222", new ArrayList<Author>(), "Titulo3", "CCCCC", LocalDate.now(), 20, "Español"))
    );

    @Override
    public void create(Book book) {
        books.add(book);
    }

    @Override
    public void update(Book book) {
        Book currentBook = findById(book.getIsbn());
        int index = books.indexOf(currentBook);
        books.set(index, book);
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findById(String s) {
        Book book = books.stream()
                .filter(a -> s.equalsIgnoreCase(a.getIsbn()))
                .findFirst()
                .orElse(null);

        return book;
    }

    public List<Author> findAuthors(String s) {
        Book book = findById(s);
        return book.getAuthors();
    }
}
