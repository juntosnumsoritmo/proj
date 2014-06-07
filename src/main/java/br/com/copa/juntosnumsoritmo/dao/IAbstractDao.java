package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.util.List;

public interface IAbstractDao<T extends AbstractDocument> {

    void salvar(T bean);

    void atualizar(T bean);
    
    void remover(T bean);

    T obter(T filtro);

    List<T> listar(T filtro);
    
    List<T> listarTodos();

}
