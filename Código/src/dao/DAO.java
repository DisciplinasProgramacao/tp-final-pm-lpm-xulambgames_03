package dao;

import java.util.List;

public interface DAO<E> { // DAO (Organização autônoma descentralizada) padrão de projeto pra salvar arquivo

    void salvarTodos(List<E> e);
    List<E> getAll();
    
}
