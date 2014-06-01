package br.com.copa.juntosnumsoritmo.validador;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.Arrays;

public class Mensagem {

    private String textoMensagem;
    private Object[] paramametros;

    public Mensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
        this.paramametros = new Object[Constantes.EMPTY];
    }
    
    public Mensagem(String textoMensagem, Object[] paramametros) {
        this.textoMensagem = textoMensagem;
        this.paramametros = paramametros;
    }
    
    public String getTextoMensagem() {
        return textoMensagem;
    }

    public void setTextoMensagem(String textoMensagem) {
        this.textoMensagem = textoMensagem;
    }

    public Object[] getParamametros() {
        Object[] retorno = null;

        if (this.paramametros != null) {
            retorno = Arrays.copyOf(this.paramametros, paramametros.length);
        }

        return retorno;
    }

    public void setParametros(Object[] params) {
        if (params != null) {
            this.paramametros = Arrays.copyOf(params, params.length);
        }
    }

}
