package br.com.copa.juntosnumsoritmo.model;

<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ranking implements Serializable, Comparable<Ranking> {
    
    private static final long serialVersionUID = 791736155792579015L;
    
    private Float pontuacao;
    private Selecao selecao;
    private List<DesempenhoAtleta> desempenhoAtletaList;

    public Ranking() {
        super();
        
        this.pontuacao = PontuacaoEnum.PONTUACAO_INICIAL.getPontos();
        this.desempenhoAtletaList = new ArrayList<DesempenhoAtleta>(Constantes.EMPTY);
    }

    public Ranking(Selecao selecao) {
        super();
        
        this.selecao = selecao;
        this.pontuacao = PontuacaoEnum.PONTUACAO_INICIAL.getPontos();
        this.desempenhoAtletaList = new ArrayList<DesempenhoAtleta>(Constantes.EMPTY);
    }
    
    @Override
    public int compareTo(Ranking o) {
        int retorno = -1;
        
        if (this.getPontuacao() != null && o.getPontuacao() != null) {
            retorno = getPontuacao().compareTo(o.getPontuacao());
        }
        
        return retorno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.selecao != null ? this.selecao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ranking other = (Ranking) obj;
        if (this.selecao != other.selecao && (this.selecao == null || !this.selecao.equals(other.selecao))) {
            return false;
        }
        return true;
    }

    public Float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public List<DesempenhoAtleta> getDesempenhoAtletaList() {
        return desempenhoAtletaList;
    }

    public void setDesempenhoAtletaList(List<DesempenhoAtleta> desempenhoAtletaList) {
        this.desempenhoAtletaList = desempenhoAtletaList;
    }
    
=======
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

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
