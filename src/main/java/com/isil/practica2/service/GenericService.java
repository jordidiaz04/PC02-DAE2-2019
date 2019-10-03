package com.isil.practica2.service;

import java.util.List;

public interface GenericService<T, K> {
    void create(T t);
    void update(T t);
    void delete(T t);
    List<T> findAll();
    T findById(K k);
}
