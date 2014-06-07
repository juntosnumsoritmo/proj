package br.com.copa.juntosnumsoritmo.dao;

import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;

public interface ISelecaoDao extends IAbstractDao<Selecao> {
    
    public List<Atleta> listarAtleta(Selecao selecao);

}
