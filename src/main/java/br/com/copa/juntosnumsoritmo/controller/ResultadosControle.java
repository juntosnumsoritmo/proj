package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtletaEnum;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class ResultadosControle extends AbstractControle {

    private static final long serialVersionUID = -1685181979582567165L;

    private Partida partida;
    private Comentario comentario;
    private List<Partida> partidaList;
    private List<Comentario> comentarioList;
    private CartesianChartModel partidaPrimeiraSelecaoChart;
    private CartesianChartModel partidaSegundaSelecaoChart;

    @Autowired
    private transient IPartidaFacade partidaFacade;

    @PostConstruct
    public void init() {
        setPartidaList(partidaFacade.listar(new Partida()));
        setComentario(new Comentario());
        setComentarioList(new ArrayList<Comentario>(Constantes.EMPTY));

        if (getPartidaList().isEmpty()) {
            setPartida(new Partida());
        } else {
            setPartida(getPartidaList().get(0));
            
            visualizarResultados();
        }
    }
    
    public void criarComentario() {
        if (StringUtil.isNotBlank(getComentario().getComentario())) {
            getComentario().setApostador(Util.recuperarUsarioLogado());
            getComentario().setPartida(getPartida());
            
            partidaFacade.criarComentario(getComentario());
            
            setComentario(new Comentario());
            setComentarioList(partidaFacade.listarComentario(partida));
            
            addMessage(Constantes.MSG_COMENTARIO_SUCESSO);
        }
    }
    
    public void visualizarComentarios() {
        setComentario(new Comentario());
        setComentarioList(partidaFacade.listarComentario(partida));
    }

    public void visualizarResultador(AjaxBehaviorEvent e) {
        visualizarResultados();
    }

    public void visualizarResultados() {
        final List<DesempenhoAtleta> desempenhoAtletaPrimeiraSelecaoList = partidaFacade.listarDesempenhoAtleta(getPartida(), getPartida().getPrimeiraSelecao());
        final List<DesempenhoAtleta> desempenhoAtletaSegundaSelecaoList = partidaFacade.listarDesempenhoAtleta(getPartida(), getPartida().getSegundaSelecao());
        
        this.partidaPrimeiraSelecaoChart = popularGrafico(desempenhoAtletaPrimeiraSelecaoList);
        this.partidaSegundaSelecaoChart = popularGrafico(desempenhoAtletaSegundaSelecaoList);
    }

    private CartesianChartModel popularGrafico(final List<DesempenhoAtleta> desempenhoAtletaList) {
        final BarChartSeries barChartTotal = new BarChartSeries();
        
        final CartesianChartModel chartModel = new CartesianChartModel();
        chartModel.addSeries(barChartTotal);
        
        DesempenhoAtleta desempenhoAtletaTotal = new DesempenhoAtleta(new Atleta(), new Selecao(), partida);
        LineChartSeries lineChartSeries;

        for (DesempenhoAtleta desempenhoAtleta : desempenhoAtletaList) {
            desempenhoAtletaTotal = DesempenhoAtletaEnum.somar(desempenhoAtletaTotal, desempenhoAtleta);

            lineChartSeries = new LineChartSeries();
            lineChartSeries.setLabel(desempenhoAtleta.getAtleta().getNome());
            lineChartSeries.set(getText(Constantes.MSG_DESEMPENHO_ATLETA_GOLS), desempenhoAtleta.getNumGols());
            lineChartSeries.set(getText(Constantes.MSG_DESEMPENHO_ATLETA_PASSES_CERTOS), desempenhoAtleta.getNumPassesCert());
            lineChartSeries.set(getText(Constantes.MSG_DESEMPENHO_ATLETA_PASSES_ERRADOS), desempenhoAtleta.getNumPassesErrad());
            lineChartSeries.set(getText(Constantes.MSG_DESEMPENHO_ATLETA_CARTAO_AMARELO), desempenhoAtleta.getNumCartAmar());
            lineChartSeries.set(getText(Constantes.MSG_DESEMPENHO_ATLETA_CARTAO_VERMELHO), desempenhoAtleta.getNumCartVerm());

            chartModel.addSeries(lineChartSeries);
        }

        chartModel.getSeries().get(0).setLabel(getText(Constantes.MSG_DESEMPENHO_ATLETA_TOTAL));
        chartModel.getSeries().get(0).set(getText(Constantes.MSG_DESEMPENHO_ATLETA_GOLS), desempenhoAtletaTotal.getNumGols());
        chartModel.getSeries().get(0).set(getText(Constantes.MSG_DESEMPENHO_ATLETA_PASSES_CERTOS), desempenhoAtletaTotal.getNumPassesCert());
        chartModel.getSeries().get(0).set(getText(Constantes.MSG_DESEMPENHO_ATLETA_PASSES_ERRADOS), desempenhoAtletaTotal.getNumPassesErrad());
        chartModel.getSeries().get(0).set(getText(Constantes.MSG_DESEMPENHO_ATLETA_CARTAO_AMARELO), desempenhoAtletaTotal.getNumCartAmar());
        chartModel.getSeries().get(0).set(getText(Constantes.MSG_DESEMPENHO_ATLETA_CARTAO_VERMELHO), desempenhoAtletaTotal.getNumCartVerm());
        
        return chartModel;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Partida> getPartidaList() {
        return partidaList;
    }

    public void setPartidaList(List<Partida> partidaList) {
        this.partidaList = partidaList;
    }

    public CartesianChartModel getPartidaPrimeiraSelecaoChart() {
        return partidaPrimeiraSelecaoChart;
    }

    public CartesianChartModel getPartidaSegundaSelecaoChart() {
        return partidaSegundaSelecaoChart;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }
    
}
