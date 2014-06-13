package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.facade.IAbstractFacade;
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

<<<<<<< HEAD
public abstract class PersistenciaControle<T extends AbstractDocument> extends AbstractControle {
=======
public abstract class PersistenciaControle<T extends Serializable> extends AbstractControle {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    
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
<<<<<<< HEAD
        if (entidade != null) {
            if (entidade.getId() == null) {
=======
        if (entidade != null && entidade instanceof AbstractDocument) {
            final AbstractDocument objeto = (AbstractDocument) entidade;
            
            if (objeto.getId() == null) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
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
