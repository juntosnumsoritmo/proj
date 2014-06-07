package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import java.util.List;

public interface IDesempenhoAtletaDao extends IAbstractDao<DesempenhoAtleta> {
    
    public List<DesempenhoAtleta> listar(Partida... partidaList);
    
}
