package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Ranking;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class PontuacaoControle extends AbstractControle {

    private static final long serialVersionUID = 5769940624548359352L;

    private Partida partida;
    private CartesianChartModel pontuacaoChart;

    @Autowired
    private transient IPartidaFacade partidaFacade;

    @PostConstruct
    public void init() {
        partida = new Partida();
        partida.setDataInicial(Util.obterDataInicialDefault(new Date()));
        partida.setDataFinal(Util.obterDataFinalDefault(new Date()));

        gerarPontuacao();
    }

    public void gerarPontuacao() {
        if (Util.isPeriodoValido(partida.getDataInicial(), partida.getDataFinal())) {
            final Map<Selecao, Ranking> rankingOficial = partidaFacade.obterChartSeriesOficial(partida);
            final Map<Selecao, Ranking> rankingApostador = partidaFacade.obterChartSeriesApostador(partida);

            final BarChartSeries chartSerieApostador = new BarChartSeries();
            chartSerieApostador.setLabel(getText(Constantes.LABEL_PONTUACAO_APOSTADOR));

            final BarChartSeries chartSerieOficial = new BarChartSeries();
            chartSerieOficial.setLabel(getText(Constantes.LABEL_PONTUACAO_OFICIAL));

            Ranking ranking;
            pontuacaoChart = new CartesianChartModel();

            for (Map.Entry<Selecao, Ranking> entry : rankingOficial.entrySet()) {
                chartSerieOficial.set(entry.getKey().getNome(), entry.getValue().getPontuacao());

                ranking = rankingApostador.get(entry.getKey());

                if (ranking == null) {
                    chartSerieApostador.set(entry.getKey().getNome(), 0);
                } else {
                    chartSerieApostador.set(entry.getKey().getNome(), ranking.getPontuacao());
                }
            }

            if (!rankingOficial.isEmpty()) {
                pontuacaoChart.addSeries(chartSerieOficial);
            }

            if (!rankingApostador.isEmpty()) {
                pontuacaoChart.addSeries(chartSerieApostador);
            }

        } else {
            addMessageError(Constantes.MSG_PERIODO_INVALIDO);
        }
    }

    public CartesianChartModel getPontuacaoChart() {
        return pontuacaoChart;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

}
