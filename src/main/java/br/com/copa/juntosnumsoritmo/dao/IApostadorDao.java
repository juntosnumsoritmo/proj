
package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.Apostador;

public interface IApostadorDao extends IAbstractDao<Apostador> {

    public Apostador verificarLoginUsuario(Apostador usuario);
    
    public Apostador obterPeloLogin(String loginUsuario);
    
}
