package br.com.copa.juntosnumsoritmo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;
    private Number seq;

    public SequenceId() {
        super();
    }

    public SequenceId(String id, Number seq) {
        this.id = id;
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getSeq() {
        return seq;
    }

    public void setSeq(Number seq) {
        this.seq = seq;
    }

}
