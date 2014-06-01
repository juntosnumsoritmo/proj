package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class PartidaControle extends PersistenciaControle<Partida> {
    
    private static final long serialVersionUID = 3309362166108673088L;
    
    private List<Selecao> selecaoList;
    
    @Override
    public void init() {
        setEntidade(new Partida());

        setFiltro(new Partida());
        
        setSelecaoList(getPartidaFacade().listarSelecao());

        listar();
    }

    @Override
    public String preSalvar() {
        setEntidade(new Partida());

        return null;
    }

    @Override
    public String preAtualizar() {
        obter();

        return null;
    }
    
    public void gerarResultado() {
        final Partida partida = getPartidaFacade().gerarResultado(getEntidade());
        
        setEntidade(partida);
        
        listar();
        
        addMessage(Constantes.MSG_PARTIDA_PLACAR_SUCESSO);
    }

    public List<Selecao> getSelecaoList() {
        return selecaoList;
    }

    public void setSelecaoList(List<Selecao> selecaoList) {
        this.selecaoList = selecaoList;
    }
    
    public boolean isGerarPlacar() {
        return getEntidade() != null && getEntidade().getId() != null && StringUtil.isBlank(getEntidade().getPlacar());
    }
    
    public IPartidaFacade getPartidaFacade() {
        return (IPartidaFacade) getFacade();
    }
    
}
