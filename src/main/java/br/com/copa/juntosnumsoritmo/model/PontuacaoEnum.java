package br.com.copa.juntosnumsoritmo.model;

import java.util.Arrays;
import java.util.List;

public enum PontuacaoEnum {

    PONTUACAO_INICIAL(50F),
    CARTAO_AMARELO(-3F),
    CARTAO_VERMELHO(-6F),
    FALTAS_COMETIDAS(-0.5F),
    FALTAS_RECEBIDAS(+0.5F),
    PASSES_ERRADOS(-0.2F),
    PASSES_CERTOS(+0.2F),
    GOLS_CONTRA(-5F),
    GOLS(+9F),
    DEFESAS_DIFICEIS(+3F),
    DEFESAS_PENALTI(+9F),
    DEFESAS_FALHAS(-5F),
    GOLS_SOFRIDOS(-1F);

    private final Float pontos;

    private PontuacaoEnum(Float pontos) {
        this.pontos = pontos;
    }

    public Float getPontos() {
        return pontos;
    }
    
    public static Ranking createRanking(Selecao selecao, List<DesempenhoAtleta> desempenhoAtletaList) {
        Ranking ranking = new Ranking(selecao);
        
        for (DesempenhoAtleta desempenhoAtleta : desempenhoAtletaList) {
            ranking = somar(ranking, desempenhoAtleta);
        }
        
        return ranking;
    }

    public static Ranking somar(Ranking origem, DesempenhoAtleta soma) {
        if (origem != null && soma != null) {
            Float pontuacao = 0F;

            if (soma.getNumCartAmar() != null) {
                pontuacao += CARTAO_AMARELO.getPontos() * soma.getNumCartAmar();
            }

            if (soma.getNumCartVerm() != null) {
                pontuacao += CARTAO_VERMELHO.getPontos() * soma.getNumCartVerm();
            }

            if (soma.getNumFaltasCom() != null) {
                pontuacao += FALTAS_COMETIDAS.getPontos() * soma.getNumFaltasCom();
            }

            if (soma.getNumFaltasRec() != null) {
                pontuacao += FALTAS_RECEBIDAS.getPontos() * soma.getNumFaltasRec();
            }

            if (soma.getNumPassesCert() != null) {
                pontuacao += PASSES_CERTOS.getPontos() * soma.getNumPassesCert();
            }

            if (soma.getNumPassesErrad() != null) {
                pontuacao += PASSES_ERRADOS.getPontos() * soma.getNumPassesErrad();
            }

            if (soma.getNumGols() != null) {
                pontuacao += GOLS.getPontos() * soma.getNumGols();
            }

            if (soma.getNumGolsContr() != null) {
                pontuacao += GOLS_CONTRA.getPontos() * soma.getNumGolsContr();
            }

            if (soma.getGoleiroGolSofrido() != null) {
                pontuacao += GOLS_SOFRIDOS.getPontos() * soma.getGoleiroGolSofrido();
            }

            if (soma.getGoleiroDefDificil() != null) {
                pontuacao += DEFESAS_DIFICEIS.getPontos() * soma.getGoleiroDefDificil();
            }

            if (soma.getGoleiroFalhaDef() != null) {
                pontuacao += DEFESAS_FALHAS.getPontos() * soma.getGoleiroFalhaDef();
            }

            if (soma.getGoleiroDefPenalti() != null) {
                pontuacao += DEFESAS_PENALTI.getPontos() * soma.getGoleiroDefPenalti();
            }
            
            if (origem.getDesempenhoAtletaList() == null) {
                origem.setDesempenhoAtletaList(Arrays.asList(soma));
            } else {
                origem.getDesempenhoAtletaList().add(soma);
            }

            if (origem.getPontuacao() == null) {
                origem.setPontuacao(pontuacao);
            } else {
                origem.setPontuacao(origem.getPontuacao() + pontuacao);
            }
        }

        return origem;
    }

}
