package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
<<<<<<< HEAD
import org.springframework.data.mongodb.core.mapping.DBRef;
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_APOSTADOR)
public class Apostador extends AbstractDocument {
    
    private static final long serialVersionUID = 7661371741694454345L;

    private String nome;
    private String login;
    private String senha;
    private Double valorInicial;
    private Integer pontos;
    private boolean admin;
<<<<<<< HEAD
    @DBRef(lazy = true) private ApostadorEscalacao escalacao;
=======
    private Escalacao escalacao;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

    public Apostador() {
        super();
    }
    
    public Apostador(Long id) {
        super(id);
    }

    public Apostador(Long id, String nome, String login, String senha, 
<<<<<<< HEAD
            Double valorInicial, Integer pontos, boolean isAdmin, ApostadorEscalacao escalacao) {
=======
            Double valorInicial, Integer pontos, boolean isAdmin, Escalacao escalacao) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        super(id);
        
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.valorInicial = valorInicial;
        this.pontos = pontos;
        this.admin = isAdmin;
        this.escalacao = escalacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

<<<<<<< HEAD
    public ApostadorEscalacao getEscalacao() {
        return escalacao;
    }

    public void setEscalacao(ApostadorEscalacao escalacao) {
=======
    public Escalacao getEscalacao() {
        return escalacao;
    }

    public void setEscalacao(Escalacao escalacao) {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        this.escalacao = escalacao;
    }

}
