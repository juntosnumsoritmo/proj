
package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.Partida;
import java.util.List;

public interface IPartidaDao extends IAbstractDao<Partida> {
    
    public List<Partida> listarPartidaNaoRealizadas();
    
}
