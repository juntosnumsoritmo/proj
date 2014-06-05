package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.Apostador;

public interface IApostadorFacade extends IAbstractFacade<Apostador> {

    public Apostador verificarLoginUsuario(Apostador usuario);
    
}
