package dao;

import java.util.List;

public interface DAO<E> {

    void salvarTodos(List<E> e);
    List<E> getAll();
    
}
