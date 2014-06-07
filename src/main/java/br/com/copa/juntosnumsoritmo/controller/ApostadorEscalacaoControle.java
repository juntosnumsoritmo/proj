package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.IApostadorEscalacaoFacade;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class ApostadorEscalacaoControle extends PersistenciaControle<ApostadorEscalacao> {

    private static final long serialVersionUID = 8089105526728867121L;

    private List<Partida> partidaList;

    private List<Atleta> atletaPrimeiraSelecaoList;
    private List<Atleta> atletaSegundaSelecaoList;

    private List<String> atletaPrimeraSelecaoSelecionadoList;
    private List<String> atletaSegundaSelecaoSelecionadoList;

    @Override
    public void init() {
        setEntidade(new ApostadorEscalacao());

        setFiltro(new ApostadorEscalacao(null, null, Util.recuperarUsarioLogado(), null));

        setEntidadeList(getFacade().listar(getFiltro()));
    }

    @Override
    public String preSalvar() {
        setEntidade(new ApostadorEscalacao());

        setPartidaList(getApostadorEscalacaoFacade().listarPartidaNaoRealizadas());

        if (getPartidaList().isEmpty()) {
            addMessageError(Constantes.MSG_APOSTADOR_ESCALACAO_SEM_PARTIDA_ABERTO);

        } else {
            getEntidade().setPartida(getPartidaList().get(0));

            carregarAtletas(null);
        }

        setAtletaPrimeraSelecaoSelecionadoList(new ArrayList<String>(Constantes.EMPTY));
        setAtletaSegundaSelecaoSelecionadoList(new ArrayList<String>(Constantes.EMPTY));

        return null;
    }

    @Override
    public void listar() {
        final boolean hasPeriodo = getFiltro().getDataInicial() != null && getFiltro().getDataFinal() != null;

        if (hasPeriodo && !Util.isPeriodoValido(getFiltro().getDataInicial(), getFiltro().getDataFinal())) {
            addMessageError(Constantes.MSG_PERIODO_INVALIDO);
        } else {
            super.listar();
        }
    }

    @Override
    public String preAtualizar() {
        obter();

        setAtletaPrimeraSelecaoSelecionadoList(new ArrayList<String>(Constantes.EMPTY));
        setAtletaSegundaSelecaoSelecionadoList(new ArrayList<String>(Constantes.EMPTY));

        carregarAtletas(null);

        for (Atleta atleta : getEntidade().getAtletaList()) {
            if (getAtletaPrimeiraSelecaoList().contains(atleta)) {
                getAtletaPrimeraSelecaoSelecionadoList().add(atleta.getId().toString());

            } else if (getAtletaSegundaSelecaoList().contains(atleta)) {
                getAtletaSegundaSelecaoSelecionadoList().add(atleta.getId().toString());

            }
        }

        return null;
    }

    @Override
    public void salvar() {
        getEntidade().setDataHora(new Date());
        getEntidade().setApostador(Util.recuperarUsarioLogado());
        getEntidade().setAtletaList(new ArrayList<Atleta>(Constantes.EMPTY));

        Atleta atleta;
        for (String id : getAtletaPrimeraSelecaoSelecionadoList()) {
            atleta = getAtletaPrimeiraSelecaoList().get(getAtletaPrimeiraSelecaoList().indexOf(new Atleta(Long.valueOf(id))));

            getEntidade().getAtletaList().add(atleta);
        }

        for (String id : getAtletaSegundaSelecaoSelecionadoList()) {
            atleta = getAtletaSegundaSelecaoList().get(getAtletaSegundaSelecaoList().indexOf(new Atleta(Long.valueOf(id))));

            getEntidade().getAtletaList().add(atleta);
        }

        super.salvar();
    }

    @Override
    public void atualizar() {
        getEntidade().setAtletaList(new ArrayList<Atleta>(Constantes.EMPTY));

        for (String id : getAtletaPrimeraSelecaoSelecionadoList()) {
            getEntidade().getAtletaList().add(new Atleta(Long.valueOf(id)));
        }

        for (String id : getAtletaSegundaSelecaoSelecionadoList()) {
            getEntidade().getAtletaList().add(new Atleta(Long.valueOf(id)));
        }

        super.atualizar();
    }

    public void carregarAtletas(AjaxBehaviorEvent event) {
        final Partida partida = getEntidade().getPartida();

        if (partida == null || partida.getPrimeiraSelecao() == null || partida.getSegundaSelecao() == null) {
            setAtletaPrimeiraSelecaoList(new ArrayList<Atleta>(Constantes.EMPTY));

            setAtletaSegundaSelecaoList(new ArrayList<Atleta>(Constantes.EMPTY));
        } else {
            setAtletaPrimeiraSelecaoList(getApostadorEscalacaoFacade().listarAtleta(partida.getPrimeiraSelecao()));

            setAtletaSegundaSelecaoList(getApostadorEscalacaoFacade().listarAtleta(partida.getSegundaSelecao()));
        }
    }

    public IApostadorEscalacaoFacade getApostadorEscalacaoFacade() {
        return (IApostadorEscalacaoFacade) getFacade();
    }

    public List<Partida> getPartidaList() {
        return partidaList;
    }

    public void setPartidaList(List<Partida> partidaList) {
        this.partidaList = partidaList;
    }

    public List<Atleta> getAtletaPrimeiraSelecaoList() {
        return atletaPrimeiraSelecaoList;
    }

    public void setAtletaPrimeiraSelecaoList(List<Atleta> atletaPrimeiraSelecaoList) {
        this.atletaPrimeiraSelecaoList = atletaPrimeiraSelecaoList;
    }

    public List<Atleta> getAtletaSegundaSelecaoList() {
        return atletaSegundaSelecaoList;
    }

    public void setAtletaSegundaSelecaoList(List<Atleta> atletaSegundaSelecaoList) {
        this.atletaSegundaSelecaoList = atletaSegundaSelecaoList;
    }

    public List<String> getAtletaPrimeraSelecaoSelecionadoList() {
        return atletaPrimeraSelecaoSelecionadoList;
    }

    public void setAtletaPrimeraSelecaoSelecionadoList(List<String> atletaPrimeraSelecaoSelecionadoList) {
        this.atletaPrimeraSelecaoSelecionadoList = atletaPrimeraSelecaoSelecionadoList;
    }

    public List<String> getAtletaSegundaSelecaoSelecionadoList() {
        return atletaSegundaSelecaoSelecionadoList;
    }

    public void setAtletaSegundaSelecaoSelecionadoList(List<String> atletaSegundaSelecaoSelecionadoList) {
        this.atletaSegundaSelecaoSelecionadoList = atletaSegundaSelecaoSelecionadoList;
    }

}
