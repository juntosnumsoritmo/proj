
package br.com.copa.juntosnumsoritmo.exception;

import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;

public class ValidacaoException extends RegraNegocioException {
    
    private ValidadorMessage validadorMessage;

    public ValidacaoException(ValidadorMessage validadorMessage) {
        this.validadorMessage = validadorMessage;
    }

    public ValidadorMessage getValidadorMessage() {
        return validadorMessage;
    }

    public void setValidadorMessage(ValidadorMessage validadorMessage) {
        this.validadorMessage = validadorMessage;
    }
    
}
