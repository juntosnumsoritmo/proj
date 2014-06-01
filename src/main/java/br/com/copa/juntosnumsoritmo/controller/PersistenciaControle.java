package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.facade.IAbstractFacade;
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PersistenciaControle<T extends Serializable> extends AbstractControle {
    
    private static final long serialVersionUID = 7746817840069420235L;

    private T entidade;
    private T filtro;
    private List<T> entidadeList;

    @Autowired
    protected transient IAbstractFacade<T> facade;

    @PostConstruct
    public abstract void init();

    public abstract String preSalvar();
    
    public abstract String preAtualizar();
    
    public void salvar() {
        getFacade().salvar(getEntidade());

        init();

        addMessage(Constantes.MSG_CADASTRO_SUCESSO);
    }

    public void atualizar() {
        getFacade().atualizar(entidade);

        init();

        addMessage(Constantes.MSG_ATUALIZACAO_SUCESSO);
    }
    
    public void salvarOuAtualizar() {
        if (entidade != null && entidade instanceof AbstractDocument) {
            final AbstractDocument objeto = (AbstractDocument) entidade;
            
            if (objeto.getId() == null) {
                salvar();
            } else {
                atualizar();
            }
        }
    }

    public void obter() {
        entidade = getFacade().obter(entidade);
    }

    public void listar() {
         entidadeList = getFacade().listar(filtro);
    }

    public IAbstractFacade<T> getFacade() {
        return facade;
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public List<T> getEntidadeList() {
        return entidadeList;
    }

    public void setEntidadeList(List<T> entidadeList) {
        this.entidadeList = entidadeList;
    }

    public void setFiltro(T filtro) {
        this.filtro = filtro;
    }

    public T getFiltro() {
        return filtro;
    }
    
}
