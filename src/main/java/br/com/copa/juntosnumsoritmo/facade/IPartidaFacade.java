package br.com.copa.juntosnumsoritmo.facade;

import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;

public interface IPartidaFacade extends IAbstractFacade<Partida> {

    public List<Selecao> listarSelecao();

    public Partida gerarResultado(Partida partida);

    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida, Selecao selecao);

    public void criarComentario(Comentario comentario);

    public List<Comentario> listarComentario(Partida partida);

}
