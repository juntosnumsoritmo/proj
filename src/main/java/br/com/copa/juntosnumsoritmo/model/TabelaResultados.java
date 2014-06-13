package br.com.copa.juntosnumsoritmo.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TabelaResultados {

    private String resutadoPartidas;
    private Ranking ranking;

    private List<Partida> partida = new ArrayList<Partida>(0);

    public TabelaResultados() {
    }

    public TabelaResultados(String resutadoPartidas, Ranking ranking) {
        this.resutadoPartidas = resutadoPartidas;
        this.ranking = ranking;
    }

    public String getResutadoPartidas() {
        return resutadoPartidas;
    }

    public void setResutadoPartidas(String resutadoPartidas) {
        this.resutadoPartidas = resutadoPartidas;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public List<Partida> getPartida() {
        return partida;
    }

    public void setPartida(List<Partida> partida) {
        this.partida = partida;
    }

}
