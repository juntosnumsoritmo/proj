package br.com.copa.juntosnumsoritmo.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

public class AbstractDocument implements Serializable {
    
    private static final long serialVersionUID = 1176433638446987549L;
    
    @Id
    private Long id;

    public AbstractDocument() {
        super();
    }

    public AbstractDocument(Long id) {
        this.id = id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final AbstractDocument other = (AbstractDocument) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + '}';
    }
    
}
