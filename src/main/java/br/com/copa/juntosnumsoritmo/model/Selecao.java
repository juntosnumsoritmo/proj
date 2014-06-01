package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Constantes.NOME_COLECAO_SELECAO)
public class Selecao extends AbstractDocument {
    
    private static final long serialVersionUID = 3734359906675328995L;

    private String nome;

    @Transient
    private String nomeAtleta;

    @DBRef @Field(value = "atletas")
    private Set<Atleta> atletaList;

    public Selecao() {
        super();
        
        this.atletaList = new HashSet<Atleta>(Constantes.EMPTY);
    }

    public Selecao(Long id) {
        super(id);
        
        this.atletaList = new HashSet<Atleta>(Constantes.EMPTY);
    }

    public Selecao(String nome, Set<Atleta> atletaList) {
        this.nome = nome;
        this.atletaList = atletaList;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(Set<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public void setNomeAtleta(String nomeAtleta) {
        this.nomeAtleta = nomeAtleta;
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

}
