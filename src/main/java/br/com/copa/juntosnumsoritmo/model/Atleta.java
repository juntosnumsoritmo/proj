package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_ATLETA)
public class Atleta extends AbstractDocument {
    
    private static final long serialVersionUID = 1269188742315395321L;

    private String nome;
    private Integer idade;
    private String pais;
    private String posicao;
    private Double pontuacao;

    public Atleta() {
        super();
    }
    
    public Atleta(Long id) {
        super(id);
    }

    public Atleta(Long id, String nome, Integer idade, String pais, String posicao, Double pontuacao) {
        super(id);

        this.nome = nome;
        this.idade = idade;
        this.pais = pais;
        this.posicao = posicao;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

}
