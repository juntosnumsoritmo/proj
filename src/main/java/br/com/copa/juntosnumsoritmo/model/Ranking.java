package br.com.copa.juntosnumsoritmo.model;

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
    
}
