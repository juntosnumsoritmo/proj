package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.dao.IAbstractDao;
import br.com.copa.juntosnumsoritmo.facade.IAbstractFacade;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFacade<T extends Serializable> implements IAbstractFacade<T> {

    @Autowired
    protected IAbstractDao<T> dao;

    public IAbstractDao<T> getDao() {
        return dao;
    }
    
}
