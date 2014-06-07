

package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Partida;
import java.util.List;

public interface IApostadorEscalacaoDao extends IAbstractDao<ApostadorEscalacao> {
    
    public List<ApostadorEscalacao> listar(Apostador apostador, Partida... partidaList);
    
}
