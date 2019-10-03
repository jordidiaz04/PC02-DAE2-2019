package com.isil.practica2.controller;

import org.springframework.ui.Model;

public interface GenericController<T, K> {
    String getList(Model model);
    String save(T t, Model model);
    String add(Model model);
    String getForUpdate(K k, Model model);
    String update(K k, T t, Model model);
    String delete(K k, Model model);
}
