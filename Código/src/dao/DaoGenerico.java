package dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoGenerico<E> implements DAO<E> {

    String nomeDoArquivo;

    public DaoGenerico(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    
    }


    @Override
    public void salvarTodos(List<E> e) {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(nomeDoArquivo))){
            for (E e2 : e) {
                o.writeObject(e2);
            }
            
        } catch (Exception exception) {

        }

        
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        List<E> lista = new ArrayList<>();
        try(ObjectInputStream objeto = new ObjectInputStream(new FileInputStream(nomeDoArquivo))) {
            E e = (E) objeto.readObject();

            while (e != null) {
                lista.add(e);
                e = (E) objeto.readObject();

            } 
            
        } 
        catch (EOFException e) {} 
        catch (IOException e) {

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return lista;
    }

}
