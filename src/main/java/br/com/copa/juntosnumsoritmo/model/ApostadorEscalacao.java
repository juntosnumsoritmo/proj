package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_APOSTADOR_ESCALACAO)
public class ApostadorEscalacao extends AbstractDocument {
   
    private static final long serialVersionUID = -3380067924482342786L;

    private String nome;
    private Date dataHora;

    @DBRef(lazy = true)
    private Apostador apostador;

    @DBRef(lazy = false)
    private Partida partida;

    @DBRef(lazy = true)
    private List<Atleta> atletaList;

    @Transient
    private String nomeSelecao;

    @Transient
    private List<Partida> partidaFiltroList;

    @Transient
    private Date dataInicial;
    
    @Transient
    private Date dataFinal;

    public ApostadorEscalacao() {
        super();

        this.atletaList = new ArrayList<Atleta>(Constantes.EMPTY);
    }

    public ApostadorEscalacao(Long id) {
        super(id);

        this.atletaList = new ArrayList<Atleta>(Constantes.EMPTY);
    }

    public ApostadorEscalacao(String nome, Date dataHora, Apostador apostador, Partida partida) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.apostador = apostador;
        this.partida = partida;

        this.atletaList = new ArrayList<Atleta>(Constantes.EMPTY);
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Apostador getApostador() {
        return apostador;
    }

    public void setApostador(Apostador apostador) {
        this.apostador = apostador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtletaListString() {
        final StringBuilder retorno = new StringBuilder();

        if (atletaList != null && !atletaList.isEmpty()) {
            for (Atleta atleta : atletaList) {
                retorno.append(atleta.getNome());
                retorno.append(", ");
            }

            retorno.deleteCharAt(retorno.lastIndexOf(","));
        }

        return retorno.toString().trim();
    }

    public String getNomeSelecao() {
        return nomeSelecao;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

    public List<Partida> getPartidaFiltroList() {
        return partidaFiltroList;
    }

    public void setPartidaFiltroList(List<Partida> partidaFiltroList) {
        this.partidaFiltroList = partidaFiltroList;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
