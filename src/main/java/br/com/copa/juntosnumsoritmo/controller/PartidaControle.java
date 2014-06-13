package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.util.Util;
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class PartidaControle extends PersistenciaControle<Partida> {
<<<<<<< HEAD

    private static final long serialVersionUID = 3309362166108673088L;

    private List<Selecao> selecaoList;

=======
    
    private static final long serialVersionUID = 3309362166108673088L;
    
    private List<Selecao> selecaoList;
    
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    @Override
    public void init() {
        setEntidade(new Partida());

        setFiltro(new Partida());
<<<<<<< HEAD

=======
        
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
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
<<<<<<< HEAD

    @Override
    public void listar() {
        final boolean hasPeriodo = getFiltro().getDataInicial() != null && getFiltro().getDataFinal() != null;
        
        if (hasPeriodo && !Util.isPeriodoValido(getFiltro().getDataInicial(), getFiltro().getDataFinal())) {
            addMessageError(Constantes.MSG_PERIODO_INVALIDO);
        } else {
            super.listar();
        }
    }

    public void gerarResultado() {
        final Partida partida = getPartidaFacade().gerarResultado(getEntidade());

        setEntidade(partida);

        listar();

=======
    
    public void gerarResultado() {
        final Partida partida = getPartidaFacade().gerarResultado(getEntidade());
        
        setEntidade(partida);
        
        listar();
        
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        addMessage(Constantes.MSG_PARTIDA_PLACAR_SUCESSO);
    }

    public List<Selecao> getSelecaoList() {
        return selecaoList;
    }

    public void setSelecaoList(List<Selecao> selecaoList) {
        this.selecaoList = selecaoList;
    }
<<<<<<< HEAD

    public boolean isGerarPlacar() {
        return getEntidade() != null && getEntidade().getId() != null && StringUtil.isBlank(getEntidade().getPlacar());
    }

    public IPartidaFacade getPartidaFacade() {
        return (IPartidaFacade) getFacade();
    }

=======
    
    public boolean isGerarPlacar() {
        return getEntidade() != null && getEntidade().getId() != null && StringUtil.isBlank(getEntidade().getPlacar());
    }
    
    public IPartidaFacade getPartidaFacade() {
        return (IPartidaFacade) getFacade();
    }
    
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
