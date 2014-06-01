
package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.Apostador;

public interface ILoginFacade {
    
    Apostador verificarUsuario(Apostador usuario);

    void salvar(Apostador usuario);
    
}
