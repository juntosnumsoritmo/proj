package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.ISelecaoFacade;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class SelecaoControle extends PersistenciaControle<Selecao> {

    private static final long serialVersionUID = -9178273080317514568L;
    
    private DualListModel<Atleta> atletaList;

    @Override
    public void init() {
        setEntidade(new Selecao());
        
        setFiltro(new Selecao());

        setAtletaList(prepareDualList(getSelecaoFacade().listarAtleta(), new ArrayList<Atleta>(Constantes.EMPTY)));
        
        listar();
    }

    @Override
    public String preSalvar() {
        setEntidade(new Selecao());
        
        setAtletaList(prepareDualList(getSelecaoFacade().listarAtleta(), new ArrayList<Atleta>(Constantes.EMPTY)));
        
        return null;
    }

    @Override
    public String preAtualizar() {
        obter();

        setAtletaList(prepareDualList(getSelecaoFacade().listarAtleta(), getEntidade().getAtletaList()));
        
        return null;
    }
    
    @Override
    public void salvarOuAtualizar() {
        getEntidade().setAtletaList(new HashSet<Atleta>(getAtletaList().getTarget()));
        
        super.salvarOuAtualizar();
    }

    public DualListModel<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(DualListModel<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    public ISelecaoFacade getSelecaoFacade() {
        return (ISelecaoFacade) getFacade();
    }
    
    private DualListModel<Atleta> prepareDualList(Collection<Atleta> source, Collection<Atleta> target) {
        final DualListModel dualListModel = new DualListModel();
        
        source.removeAll(target);
        
        dualListModel.setSource(new ArrayList(source));
        dualListModel.setTarget(new ArrayList(target));
        
        return dualListModel;
    }
    
}
