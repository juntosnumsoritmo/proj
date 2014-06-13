package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_DESEMPENHO_ATLETA)
public class DesempenhoAtleta extends AbstractDocument {

    private static final long serialVersionUID = -1805277621756101403L;

    private Integer numGols;
    private Integer numGolsContr;
    private Integer numFaltasCom;
    private Integer numFaltasRec;
    private Integer numCartAmar;
    private Integer numCartVerm;
    private Integer numPassesCert;
    private Integer numPassesErrad;
    private Integer goleiroGolSofrido;
    private Integer goleiroDefDificil;
    private Integer goleiroDefPenalti;
    private Integer goleiroFalhaDef;

<<<<<<< HEAD
    @DBRef(lazy = false)
    private Atleta atleta;

    @DBRef(lazy = false)
    private Partida partida;

    @DBRef(lazy = false)
=======
    @DBRef
    private Atleta atleta;

    @DBRef
    private Partida partida;

    @DBRef
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    private Selecao selecao;

    public DesempenhoAtleta() {
        super();
    }

    public DesempenhoAtleta(Atleta atleta, Selecao selecao, Partida partida) {
        super();

        this.atleta = atleta;
        this.selecao = selecao;
        this.partida = partida;
        
        this.numGols = 0;
        this.numGolsContr = 0;
        this.numFaltasCom = 0;
        this.numFaltasRec = 0;
        this.numCartAmar = 0;
        this.numCartVerm = 0;
        this.numPassesCert = 0;
        this.numPassesErrad = 0;
        this.goleiroGolSofrido = 0;
        this.goleiroDefDificil = 0;
        this.goleiroDefPenalti = 0;
        this.goleiroFalhaDef = 0;
    }

    public DesempenhoAtleta(Long id, Integer numGols, Integer numGolsContr,
            Integer numFaltasCom, Integer numFaltasRec, Integer numCartAmar,
            Integer numCartVerm, Integer numPassesCert, Integer numPassesErrad,
            Integer goleiroGolSofrido, Integer goleiroDefDificil, Integer goleiroDefPenalti,
            Integer goleiroFalhaDef, Atleta atleta, Partida partida) {
        super(id);

        this.numGols = numGols;
        this.numGolsContr = numGolsContr;
        this.numFaltasCom = numFaltasCom;
        this.numFaltasRec = numFaltasRec;
        this.numCartAmar = numCartAmar;
        this.numCartVerm = numCartVerm;
        this.numPassesCert = numPassesCert;
        this.numPassesErrad = numPassesErrad;
        this.goleiroGolSofrido = goleiroGolSofrido;
        this.goleiroDefDificil = goleiroDefDificil;
        this.goleiroDefPenalti = goleiroDefPenalti;
        this.goleiroFalhaDef = goleiroFalhaDef;
        this.atleta = atleta;
        this.partida = partida;
    }

    public Integer getNumGols() {
        return numGols;
    }

    public void setNumGols(Integer numGols) {
        this.numGols = numGols;
    }

    public Integer getNumGolsContr() {
        return numGolsContr;
    }

    public void setNumGolsContr(Integer numGolsContr) {
        this.numGolsContr = numGolsContr;
    }

    public Integer getNumFaltasCom() {
        return numFaltasCom;
    }

    public void setNumFaltasCom(Integer numFaltasCom) {
        this.numFaltasCom = numFaltasCom;
    }

    public Integer getNumFaltasRec() {
        return numFaltasRec;
    }

    public void setNumFaltasRec(Integer numFaltasRec) {
        this.numFaltasRec = numFaltasRec;
    }

    public Integer getNumCartAmar() {
        return numCartAmar;
    }

    public void setNumCartAmar(Integer numCartAmar) {
        this.numCartAmar = numCartAmar;
    }

    public Integer getNumCartVerm() {
        return numCartVerm;
    }

    public void setNumCartVerm(Integer numCartVerm) {
        this.numCartVerm = numCartVerm;
    }

    public Integer getNumPassesCert() {
        return numPassesCert;
    }

    public void setNumPassesCert(Integer numPassesCert) {
        this.numPassesCert = numPassesCert;
    }

    public Integer getNumPassesErrad() {
        return numPassesErrad;
    }

    public void setNumPassesErrad(Integer numPassesErrad) {
        this.numPassesErrad = numPassesErrad;
    }

    public Integer getGoleiroGolSofrido() {
        return goleiroGolSofrido;
    }

    public void setGoleiroGolSofrido(Integer goleiroGolSofrido) {
        this.goleiroGolSofrido = goleiroGolSofrido;
    }

    public Integer getGoleiroDefDificil() {
        return goleiroDefDificil;
    }

    public void setGoleiroDefDificil(Integer goleiroDefDificil) {
        this.goleiroDefDificil = goleiroDefDificil;
    }

    public Integer getGoleiroDefPenalti() {
        return goleiroDefPenalti;
    }

    public void setGoleiroDefPenalti(Integer goleiroDefPenalti) {
        this.goleiroDefPenalti = goleiroDefPenalti;
    }

    public Integer getGoleiroFalhaDef() {
        return goleiroFalhaDef;
    }

    public void setGoleiroFalhaDef(Integer goleiroFalhaDef) {
        this.goleiroFalhaDef = goleiroFalhaDef;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.atleta != null ? this.atleta.hashCode() : 0);
        hash = 67 * hash + (this.partida != null ? this.partida.hashCode() : 0);
        hash = 67 * hash + (this.selecao != null ? this.selecao.hashCode() : 0);
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
        final DesempenhoAtleta other = (DesempenhoAtleta) obj;
        if (this.atleta != other.atleta && (this.atleta == null || !this.atleta.equals(other.atleta))) {
            return false;
        }
        if (this.partida != other.partida && (this.partida == null || !this.partida.equals(other.partida))) {
            return false;
        }
        if (this.selecao != other.selecao && (this.selecao == null || !this.selecao.equals(other.selecao))) {
            return false;
        }
        return true;
    }

}
