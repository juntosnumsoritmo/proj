package br.com.copa.juntosnumsoritmo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Escalacao {

    @Id
    private Integer id;
    
    private Date dataHora;
    private List<Atleta> atletaList = new ArrayList<Atleta>(0);

    public Escalacao() {
        super();
    }

    public Escalacao(Integer id) {
        this.id = id;
    }
    
    public Escalacao(Integer id, Date dataHora, List<Atleta> atleta) {
        this.id = id;
        this.dataHora = dataHora;
        this.atletaList = atleta;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
