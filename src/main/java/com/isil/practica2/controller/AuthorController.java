package com.isil.practica2.controller;

import com.isil.practica2.model.Author;
import com.isil.practica2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController implements GenericController<Author, String> {
    @Autowired
    AuthorService service;

    @GetMapping("/authors")
    public String getList(Model model) {
        List<Author> authors = service.findAll();
        model.addAttribute("authors", authors);
        return "/author/index";
    }

    @GetMapping("/authors/add")
    public String add(Model model) {
        model.addAttribute("author", new Author());
        return "/author/add";
    }

    @PostMapping("/authors/save")
    public String save(Author author, Model model) {
        service.create(author);
        return getList(model);
    }

    @GetMapping("/authors/edit/{dni}")
    public String getForUpdate(@PathVariable String dni, Model model) {
        Author author = service.findById(dni);
        model.addAttribute("author", author);
        return "/author/edit";
    }

    @PostMapping("/authors/update/{dni}")
    public String update(@PathVariable String dni, Author author, Model model) {
        service.update(author);
        return getList(model);
    }

    @GetMapping("/authors/delete/{dni}")
    public String delete(@PathVariable String dni, Model model) {
        Author author = service.findById(dni);
        service.delete(author);
        return getList(model);
    }
}
