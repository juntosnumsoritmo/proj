package br.com.copa.juntosnumsoritmo.dao;

<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.util.List;

public interface IAbstractDao<T extends AbstractDocument> {
=======
import java.io.Serializable;
import java.util.List;

public interface IAbstractDao<T extends Serializable> {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

    void salvar(T bean);

    void atualizar(T bean);
    
    void remover(T bean);

    T obter(T filtro);

    List<T> listar(T filtro);
<<<<<<< HEAD
    
    List<T> listarTodos();
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

}
