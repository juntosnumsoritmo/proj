package br.com.copa.juntosnumsoritmo.facade;

<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Ranking;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;
import java.util.Map;
=======
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import java.util.List;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

public interface IPartidaFacade extends IAbstractFacade<Partida> {

    public List<Selecao> listarSelecao();

    public Partida gerarResultado(Partida partida);

    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida, Selecao selecao);

    public void criarComentario(Comentario comentario);

    public List<Comentario> listarComentario(Partida partida);
<<<<<<< HEAD
    
    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida);
    
    public List<ApostadorEscalacao> listarApostadorEscalacao(Partida partida, Apostador apostador);

    public Map<Selecao, Ranking> obterChartSeriesOficial(Partida partida);

    public Map<Selecao, Ranking> obterChartSeriesApostador(Partida partida);
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

}
