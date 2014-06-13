package br.com.copa.juntosnumsoritmo.model;

import static br.com.copa.juntosnumsoritmo.util.Util.gerarValor;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.ArrayList;
import java.util.List;

public enum DesempenhoAtletaEnum {

    GOL(5),
    GOL_CONTRA(0),
    FALTAS_COMETIDAS(1),
    FALTAS_RECEBIDAS(3),
<<<<<<< HEAD
    CARTAO_AMARELO(2),
    CARTAO_VERMELHO(2),
=======
    CARTAO_AMARELO(1),
    CARTAO_VERMELHO(1),
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    PASSES_CERTOS(10),
    PASSES_ERRADOS(8),
    GOLEIRO_GOL_SOFRIDO(1),
    GOLEIRO_DEFESA_DIFICIL(3),
    GOLEIRO_DEFESA_PENALTI(0),
    GOLEIRO_FALHA_DEFESA(1);

    private final Integer maximo;

    private DesempenhoAtletaEnum(Integer maximo) {
        this.maximo = maximo;
    }

    public Integer getValor() {
        return gerarValor(this.maximo);
    }

    /**
     * Cria uma instancia do Desempenho de um Atleta numa partida.
     *
     * @param atleta Atleta
     * @param selecao Selecao do atleta
     * @param partida Partida
     * @return instancia do Desempenho de um Atleta numa partida.
     */
    public static DesempenhoAtleta createDesempenhoAtleta(Atleta atleta, Selecao selecao, Partida partida) {
        final DesempenhoAtleta desempenhoAtleta = new DesempenhoAtleta(atleta, selecao, partida);
        desempenhoAtleta.setGoleiroDefDificil(GOLEIRO_DEFESA_DIFICIL.getValor());
        desempenhoAtleta.setGoleiroDefPenalti(GOLEIRO_DEFESA_PENALTI.getValor());
        desempenhoAtleta.setGoleiroFalhaDef(GOLEIRO_FALHA_DEFESA.getValor());
        desempenhoAtleta.setGoleiroGolSofrido(GOLEIRO_GOL_SOFRIDO.getValor());
        desempenhoAtleta.setNumCartAmar(CARTAO_AMARELO.getValor());
        desempenhoAtleta.setNumCartVerm(CARTAO_VERMELHO.getValor());
        desempenhoAtleta.setNumFaltasCom(FALTAS_COMETIDAS.getValor());
        desempenhoAtleta.setNumFaltasRec(FALTAS_RECEBIDAS.getValor());
        desempenhoAtleta.setNumPassesCert(PASSES_CERTOS.getValor());
        desempenhoAtleta.setNumPassesErrad(PASSES_CERTOS.getValor());
        desempenhoAtleta.setNumGolsContr(GOL_CONTRA.getValor());

        if (selecao != null && partida != null) {
            if (selecao.equals(partida.getPrimeiraSelecao())) {
                desempenhoAtleta.setNumGols(gerarValor(partida.getPlacarPrimeiraSelecao()));
            } else if (selecao.equals(partida.getSegundaSelecao())) {
                desempenhoAtleta.setNumGols(gerarValor(partida.getPlacarSegundaSelecao()));
            }
        }
        return desempenhoAtleta;
    }

    /**
     * Obtem a soma entre o desempenho de um atleta.
     *
     * @param desempenhoOrigem Desempenho do atleta de origem
     * @param desempenhoSoma Desempenho do atleta que sera somado
     * @return soma entre o desempenho de um atleta
     */
    public static DesempenhoAtleta somar(DesempenhoAtleta desempenhoOrigem, DesempenhoAtleta desempenhoSoma) {
        if (desempenhoOrigem != null && desempenhoSoma != null) {
            if (desempenhoOrigem.getGoleiroDefDificil() != null && desempenhoSoma.getGoleiroDefDificil() != null) {
                desempenhoOrigem.setGoleiroDefDificil(desempenhoOrigem.getGoleiroDefDificil() + desempenhoSoma.getGoleiroDefDificil());
            }

            if (desempenhoOrigem.getGoleiroDefPenalti() != null && desempenhoSoma.getGoleiroDefPenalti() != null) {
                desempenhoOrigem.setGoleiroDefPenalti(desempenhoOrigem.getGoleiroDefPenalti() + desempenhoSoma.getGoleiroDefPenalti());
            }

            if (desempenhoOrigem.getGoleiroFalhaDef() != null && desempenhoSoma.getGoleiroFalhaDef() != null) {
                desempenhoOrigem.setGoleiroFalhaDef(desempenhoOrigem.getGoleiroFalhaDef() + desempenhoSoma.getGoleiroFalhaDef());
            }

            if (desempenhoOrigem.getGoleiroGolSofrido() != null && desempenhoSoma.getGoleiroGolSofrido() != null) {
                desempenhoOrigem.setGoleiroGolSofrido(desempenhoOrigem.getGoleiroGolSofrido() + desempenhoSoma.getGoleiroGolSofrido());
            }

            if (desempenhoOrigem.getNumCartAmar() != null && desempenhoSoma.getNumCartAmar() != null) {
                desempenhoOrigem.setNumCartAmar(desempenhoOrigem.getNumCartAmar() + desempenhoSoma.getNumCartAmar());
            }

            if (desempenhoOrigem.getNumCartVerm() != null && desempenhoSoma.getNumCartVerm() != null) {
                desempenhoOrigem.setNumCartVerm(desempenhoOrigem.getNumCartVerm() + desempenhoSoma.getNumCartVerm());
            }

            if (desempenhoOrigem.getNumFaltasCom() != null && desempenhoSoma.getNumFaltasCom() != null) {
                desempenhoOrigem.setNumFaltasCom(desempenhoOrigem.getNumFaltasCom() + desempenhoSoma.getNumFaltasCom());
            }

            if (desempenhoOrigem.getNumFaltasRec() != null && desempenhoSoma.getNumFaltasRec() != null) {
                desempenhoOrigem.setNumFaltasRec(desempenhoOrigem.getNumFaltasRec() + desempenhoSoma.getNumFaltasRec());
            }

            if (desempenhoOrigem.getNumPassesCert() != null && desempenhoSoma.getNumPassesCert() != null) {
                desempenhoOrigem.setNumPassesCert(desempenhoOrigem.getNumPassesCert() + desempenhoSoma.getNumPassesCert());
            }

            if (desempenhoOrigem.getNumPassesErrad() != null && desempenhoSoma.getNumPassesErrad() != null) {
                desempenhoOrigem.setNumPassesErrad(desempenhoOrigem.getNumPassesErrad() + desempenhoSoma.getNumPassesErrad());
            }

            if (desempenhoOrigem.getNumGolsContr() != null && desempenhoSoma.getNumGolsContr() != null) {
                desempenhoOrigem.setNumGolsContr(desempenhoOrigem.getNumGolsContr() + desempenhoSoma.getNumGolsContr());
            }

            if (desempenhoOrigem.getNumGols() != null && desempenhoSoma.getNumGols() != null) {
                desempenhoOrigem.setNumGols(desempenhoOrigem.getNumGols() + desempenhoSoma.getNumGols());
            }
        }

        return desempenhoOrigem;
    }

    /**
     * Obtem o desempenho dos atletas escalados numa Partida.
     *
     * @param partida Partida
     * @return desempenho dos atletas escalados numa Partida.
     */
    public static List<DesempenhoAtleta> createDesempenhoAtleta(Partida partida) {
        final List<DesempenhoAtleta> desempenhoAtletaList = new ArrayList<DesempenhoAtleta>(Constantes.EMPTY);

        if (partida != null && partida.getPrimeiraSelecao() != null && partida.getSegundaSelecao() != null) {
            final List<Atleta> atletaPrimeiraSelecaoList = new ArrayList<Atleta>(partida.getPrimeiraSelecao().getAtletaList());
            final List<Atleta> atletaSegundaSelecaoList = new ArrayList<Atleta>(partida.getSegundaSelecao().getAtletaList());
            DesempenhoAtleta desempenhoAtleta = null;
<<<<<<< HEAD
            Atleta atleta = null;
            int gols = 0;
            
            for (int i = 0; i < atletaPrimeiraSelecaoList.size(); i++) {
                atleta = atletaPrimeiraSelecaoList.get(i);
                desempenhoAtleta = createDesempenhoAtleta(atleta, partida.getPrimeiraSelecao(), partida);
                
                gols += desempenhoAtleta.getNumGols();

                if (gols > partida.getPlacarPrimeiraSelecao()) {
                    while (gols > partida.getPlacarPrimeiraSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
                } else if (gols > partida.getPlacarPrimeiraSelecao() && i+1 == atletaPrimeiraSelecaoList.size()) {
=======
            int gols = 0;

            for (Atleta atleta : atletaPrimeiraSelecaoList) {
                desempenhoAtleta = createDesempenhoAtleta(atleta, partida.getPrimeiraSelecao(), partida);

                gols += desempenhoAtleta.getNumGols();

                if (desempenhoAtleta.getNumGols() > partida.getPlacarPrimeiraSelecao()) {
                    while (desempenhoAtleta.getNumGols() > partida.getPlacarPrimeiraSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
                } else if (gols > partida.getPlacarPrimeiraSelecao() && atletaPrimeiraSelecaoList.indexOf(atleta) == atletaPrimeiraSelecaoList.size() - 1) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
                    while (gols > partida.getPlacarPrimeiraSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
<<<<<<< HEAD
                } else if (gols < partida.getPlacarPrimeiraSelecao() && i+1 == atletaPrimeiraSelecaoList.size()) {
=======
                } else if (gols < partida.getPlacarPrimeiraSelecao() && atletaPrimeiraSelecaoList.indexOf(atleta) == atletaPrimeiraSelecaoList.size() - 1) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
                    while (gols < partida.getPlacarPrimeiraSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() + 1);
                        gols++;
                    }
                }

                desempenhoAtletaList.add(desempenhoAtleta);
            }

            gols = 0;

<<<<<<< HEAD
            for (int i = 0; i < atletaSegundaSelecaoList.size(); i++) {
                atleta = atletaSegundaSelecaoList.get(i);
=======
            for (Atleta atleta : atletaSegundaSelecaoList) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
                desempenhoAtleta = createDesempenhoAtleta(atleta, partida.getSegundaSelecao(), partida);

                gols += desempenhoAtleta.getNumGols();

<<<<<<< HEAD
                if (gols > partida.getPlacarSegundaSelecao()) {
                    while (gols > partida.getPlacarSegundaSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
                } else if (gols > partida.getPlacarSegundaSelecao() && i+1 == atletaSegundaSelecaoList.size()) {
=======
                if (desempenhoAtleta.getNumGols() > partida.getPlacarSegundaSelecao()) {
                    while (desempenhoAtleta.getNumGols() > partida.getPlacarSegundaSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
                } else if (gols > partida.getPlacarSegundaSelecao() && atletaSegundaSelecaoList.indexOf(atleta) == atletaSegundaSelecaoList.size() - 1) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
                    while (gols > partida.getPlacarSegundaSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() - 1);
                        gols--;
                    }
<<<<<<< HEAD
                } else if (gols < partida.getPlacarSegundaSelecao() && i+1 == atletaSegundaSelecaoList.size()) {
=======
                } else if (gols < partida.getPlacarSegundaSelecao() && atletaSegundaSelecaoList.indexOf(atleta) == atletaSegundaSelecaoList.size() - 1) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
                    while (gols < partida.getPlacarSegundaSelecao()) {
                        desempenhoAtleta.setNumGols(desempenhoAtleta.getNumGols() + 1);
                        gols++;
                    }
                }

                desempenhoAtletaList.add(desempenhoAtleta);
            }
        }

        return desempenhoAtletaList;
    }

}
