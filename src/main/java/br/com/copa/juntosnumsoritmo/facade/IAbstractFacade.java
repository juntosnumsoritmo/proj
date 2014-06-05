
package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.util.List;

public interface IAbstractFacade<T extends AbstractDocument> {
    
    void salvar(T bean);
    
    void atualizar(T bean);
    
    void remover(T bean);
    
    void validar(T bean);
    
    T obter(T filtro);
    
    List<T> listar(T filtro);
    
    List<T> listarTodos();
    
}
