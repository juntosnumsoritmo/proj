package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
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
    private Escalacao escalacao;

    public Apostador() {
        super();
    }
    
    public Apostador(Long id) {
        super(id);
    }

    public Apostador(Long id, String nome, String login, String senha, 
            Double valorInicial, Integer pontos, boolean isAdmin, Escalacao escalacao) {
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

    public Escalacao getEscalacao() {
        return escalacao;
    }

    public void setEscalacao(Escalacao escalacao) {
        this.escalacao = escalacao;
    }

}
