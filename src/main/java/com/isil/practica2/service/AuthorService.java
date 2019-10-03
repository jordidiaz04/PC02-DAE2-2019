package com.isil.practica2.service;

import com.isil.practica2.model.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorService implements GenericService<Author, String> {
    List<Author> authors = new ArrayList<>(
            Arrays.asList(
                    new Author("71893721", "Juan", "Perez", "Sosa", 54, ""),
                    new Author("09963820", "Maria", "Delgado", "Copare", 35, ""))
    );

    @Override
    public void create(Author author) {
        authors.add(author);
    }

    @Override
    public void update(Author author) {
        Author currentAuthor = findById(author.getDni());
        int index = authors.indexOf(currentAuthor);
        authors.set(index, author);
    }

    @Override
    public void delete(Author author) {
        authors.remove(author);
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public Author findById(String s) {
        Author author = authors.stream()
                .filter(a -> s.equalsIgnoreCase(a.getDni()))
                .findFirst()
                .orElse(null);

        return author;
    }
}
