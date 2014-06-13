package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.dao.IAbstractDao;
import br.com.copa.juntosnumsoritmo.facade.IAbstractFacade;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFacade<T extends AbstractDocument> implements IAbstractFacade<T> {
=======
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFacade<T extends Serializable> implements IAbstractFacade<T> {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

    @Autowired
    protected IAbstractDao<T> dao;

    public IAbstractDao<T> getDao() {
        return dao;
    }
<<<<<<< HEAD

    @Override
    public void salvar(T bean) {
        validar(bean);
        
        getDao().salvar(bean);
    }

    @Override
    public void atualizar(T bean) {
        validar(bean);
        
        getDao().atualizar(bean);
    }

    @Override
    public void remover(T bean) {
        getDao().remover(bean);
    }

    @Override
    public T obter(T filtro) {
        return getDao().obter(filtro);
    }
    
    @Override
    public List<T> listar(T filtro) {
        return getDao().listar(filtro);
    }
    
    @Override
    public List<T> listarTodos() {
        return getDao().listarTodos();
    }
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    
}
