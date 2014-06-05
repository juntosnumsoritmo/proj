package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;

public interface ISelecaoFacade extends IAbstractFacade<Selecao> {
    
    public List<Atleta> listarAtleta();

}
