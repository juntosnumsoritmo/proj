package br.com.copa.juntosnumsoritmo.exception;

import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.Arrays;

public class RegraNegocioException extends RuntimeException {

    private Object[] paramametros;

    public RegraNegocioException() {
        super(Constantes.MSG_ERRO_REGRA_NEGOCIO);
        this.paramametros = new Object[Constantes.EMPTY];
    }
    
    public RegraNegocioException(String message, Object... paramametros) {
        super(message);
        this.paramametros = paramametros;
    }

    public RegraNegocioException(String message, Throwable cause, Object... paramametros) {
        super(message, cause);
        this.paramametros = paramametros;
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
