
package br.com.copa.juntosnumsoritmo.facade;

import java.io.Serializable;
import java.util.List;

public interface IAbstractFacade<T extends Serializable> {
    
    void salvar(T bean);
    
    void atualizar(T bean);
    
    void remover(T bean);
    
    void validar(T bean);
    
    T obter(T filtro);
    
    List<T> listar(T filtro);
    
}
