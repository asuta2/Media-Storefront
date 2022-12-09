package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface dao <T>{
    T getById(int id);
    T add(T item);
    T update(T item);
    void delete(int id);
    List<T> getAll();
}
