package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;

public interface IApostadorEscalacaoFacade extends IAbstractFacade<ApostadorEscalacao> {

    public List<Atleta> listarAtleta(Selecao selecao);

    public List<Partida> listarPartidaNaoRealizadas();

}
