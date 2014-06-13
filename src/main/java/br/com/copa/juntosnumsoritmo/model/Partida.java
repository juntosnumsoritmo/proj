package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.util.StringUtil;
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_PARTIDA)
public class Partida extends AbstractDocument {

    private static final long serialVersionUID = 4279243967440307225L;

    private Date dataHora;
    private String localizacao;
    private Integer placarPrimeiraSelecao;
    private Integer placarSegundaSelecao;

    @DBRef
    private Selecao primeiraSelecao;
    @DBRef
    private Selecao segundaSelecao;

    @Transient
    private Date dataInicial;
    @Transient
    private Date dataFinal;
    @Transient
    private String nomeSelecao;
    @Transient
    private Set<Selecao> selecaoList;

    public Partida() {
        super();

        this.selecaoList = new HashSet<Selecao>(Constantes.EMPTY);
        this.primeiraSelecao = new Selecao();
        this.segundaSelecao = new Selecao();
    }

    public Partida(Long id) {
        super(id);

        this.selecaoList = new HashSet<Selecao>(Constantes.EMPTY);
        this.primeiraSelecao = new Selecao();
        this.segundaSelecao = new Selecao();
    }

    public Partida(Long id, Date dataHora, String localizacao) {
        super(id);
        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.selecaoList = new HashSet<Selecao>(Constantes.EMPTY);
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Selecao getPrimeiraSelecao() {
        return primeiraSelecao;
    }

    public void setPrimeiraSelecao(Selecao primeiraSelecao) {
        this.primeiraSelecao = primeiraSelecao;
    }

    public Selecao getSegundaSelecao() {
        return segundaSelecao;
    }

    public void setSegundaSelecao(Selecao segundaSelecao) {
        this.segundaSelecao = segundaSelecao;
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

    public String getNomeSelecao() {
        return nomeSelecao;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

    public Set<Selecao> getSelecaoList() {
        return selecaoList;
    }

    public void setSelecaoList(Set<Selecao> selecaoList) {
        this.selecaoList = selecaoList;
    }

    public Integer getPlacarPrimeiraSelecao() {
        return placarPrimeiraSelecao;
    }

    public void setPlacarPrimeiraSelecao(Integer placarPrimeiraSelecao) {
        this.placarPrimeiraSelecao = placarPrimeiraSelecao;
    }

    public Integer getPlacarSegundaSelecao() {
        return placarSegundaSelecao;
    }

    public void setPlacarSegundaSelecao(Integer placarSegundaSelecao) {
        this.placarSegundaSelecao = placarSegundaSelecao;
    }

    public String getPlacar() {
        return Util.formatarPlacar(placarPrimeiraSelecao, placarSegundaSelecao);
    }

    public String getDescricao() {
        final StringBuilder retorno = new StringBuilder();

        if (getPrimeiraSelecao() != null && getSegundaSelecao() != null) {
            retorno.append(Util.formatarDataHora(dataHora));
            retorno.append(Constantes.SEPARADOR);
            retorno.append(getPrimeiraSelecao().getNome());
            retorno.append(Constantes.SEPARADOR_PLACAR);
            retorno.append(getSegundaSelecao().getNome());
<<<<<<< HEAD

            if (StringUtil.isNotBlank(getPlacar())) {
                retorno.append(Constantes.SEPARADOR);
                retorno.append(getPlacar());
            }
=======
            retorno.append(Constantes.SEPARADOR);
            retorno.append(getPlacar());
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        }

        return retorno.toString();
    }

}
