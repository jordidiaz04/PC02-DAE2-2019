package com.isil.practica2.controller;

import com.isil.practica2.model.Author;
import com.isil.practica2.model.Book;
import com.isil.practica2.service.AuthorService;
import com.isil.practica2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController implements GenericController<Book, String> {
    @Autowired
    BookService service;
    @Autowired
    AuthorService serviceAuthor;

    @GetMapping("/books")
    public String getList(Model model) {
        List<Book> books = service.findAll();
        model.addAttribute("books", books);
        return "/book/index";
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "/book/add";
    }

    @PostMapping("/books/save")
    public String save(Book book, Model model) {
        service.create(book);
        return getList(model);
    }

    @GetMapping("/books/edit/{isbn}")
    public String getForUpdate(@PathVariable String isbn, Model model) {
        Book book = service.findById(isbn);
        model.addAttribute("book", book);
        return "/book/edit";
    }

    @PostMapping("/books/update/{isbn}")
    public String update(@PathVariable String isbn, Book book, Model model) {
        service.update(book);
        return getList(model);
    }

    @GetMapping("/books/delete/{isbn}")
    public String delete(@PathVariable String isbn, Model model) {
        Book book = service.findById(isbn);
        service.delete(book);
        return getList(model);
    }

    @GetMapping("/books/authors/{isbn}")
    public String getAuthors(@PathVariable String isbn, Model model) {
        List<Author> allAuthors = serviceAuthor.findAll();
        List<Author> authors = service.findAuthors(isbn);
        model.addAttribute("authors", authors);
        model.addAttribute("allAuthors", allAuthors);
        return "/book/add-authors";
    }
}
