package br.com.copa.juntosnumsoritmo.model;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.Date;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constantes.NOME_COLECAO_COMENTARIO)
public class Comentario extends AbstractDocument {
    
    private static final long serialVersionUID = 4705935368284102335L;

    private String comentario;
    private Date dataHora;
    @DBRef(lazy = true) private Apostador apostador;
    @DBRef(lazy = true) private Partida partida;

    public Comentario() {
        super();
        
        this.apostador = new Apostador();
        this.partida = new Partida();
    }
    
    public Comentario(Long id) {
        super(id);
        
        this.apostador = new Apostador();
        this.partida = new Partida();
    }

    public Comentario(String comentario, Apostador apostador, Partida partida) {
        this.comentario = comentario;
        this.apostador = apostador;
        this.partida = partida;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Apostador getApostador() {
        return apostador;
    }

    public void setApostador(Apostador apostador) {
        this.apostador = apostador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getDataHoraFormatada() {
        return dataHora == null ? "" : Util.formatarDataHora(dataHora);
    }
    
}
