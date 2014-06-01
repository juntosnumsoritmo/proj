package br.com.copa.juntosnumsoritmo.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ranking {

    private String infoJogador;
    private String infoUsuario;

    private List<Apostador> apostador = new ArrayList<Apostador>(0);
    private List<Atleta> atleta = new ArrayList<Atleta>(0);
    private List<TabelaResultados> resultados = new ArrayList<TabelaResultados>(0);

    public Ranking() {
        super();
    }

    public Ranking(String infoJogador, String infoUsuario) {
        this.infoJogador = infoJogador;
        this.infoUsuario = infoUsuario;
    }

    public String getInfoJogador() {
        return infoJogador;
    }

    public void setInfoJogador(String infoJogador) {
        this.infoJogador = infoJogador;
    }

    public String getInfoUsuario() {
        return infoUsuario;
    }

    public void setInfoUsuario(String infoUsuario) {
        this.infoUsuario = infoUsuario;
    }

    public List<Apostador> getApostador() {
        return apostador;
    }

    public void setApostador(List<Apostador> apostador) {
        this.apostador = apostador;
    }

    public List<Atleta> getAtleta() {
        return atleta;
    }

    public void setAtleta(List<Atleta> atleta) {
        this.atleta = atleta;
    }

    public List<TabelaResultados> getResultados() {
        return resultados;
    }

    public void setResultados(List<TabelaResultados> resultados) {
        this.resultados = resultados;
    }

}
