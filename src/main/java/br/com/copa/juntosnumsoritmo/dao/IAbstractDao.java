package br.com.copa.juntosnumsoritmo.dao;

import java.io.Serializable;
import java.util.List;

public interface IAbstractDao<T extends Serializable> {

    void salvar(T bean);

    void atualizar(T bean);
    
    void remover(T bean);

    T obter(T filtro);

    List<T> listar(T filtro);

}
