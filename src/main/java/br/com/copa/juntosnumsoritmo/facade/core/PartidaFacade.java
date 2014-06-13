package br.com.copa.juntosnumsoritmo.facade.core;

<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.dao.IApostadorEscalacaoDao;
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import br.com.copa.juntosnumsoritmo.dao.IComentarioDao;
import br.com.copa.juntosnumsoritmo.dao.IDesempenhoAtletaDao;
import br.com.copa.juntosnumsoritmo.dao.ISelecaoDao;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtletaEnum;
import br.com.copa.juntosnumsoritmo.model.Partida;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.PontuacaoEnum;
import br.com.copa.juntosnumsoritmo.model.Ranking;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.util.Util;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
=======
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaFacade extends AbstractFacade<Partida> implements IPartidaFacade {

    @Autowired
    private ISelecaoDao selecaoDao;

    @Autowired
    private IDesempenhoAtletaDao desempenhoAtletaDao;

    @Autowired
    private IComentarioDao comentarioDao;

<<<<<<< HEAD
    @Autowired
    private IApostadorEscalacaoDao apostadorEscalacaoDao;

    @Override
=======
    public void salvar(Partida bean) {
        validar(bean);

        getDao().salvar(bean);
    }

    public void atualizar(Partida bean) {
        validar(bean);

        getDao().atualizar(bean);
    }

    public void remover(Partida bean) {
        getDao().remover(bean);
    }

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void validar(Partida bean) {
        final ValidadorMessage validadorMessage = new ValidadorMessage();

        if (bean.getDataHora() == null) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_PARTIDA_DATA_VAZIA));
        }

        if (bean.getPrimeiraSelecao() == null || bean.getSegundaSelecao() == null) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_PARTIDA_SEM_SELECAO));
        } else if (bean.getPrimeiraSelecao().equals(bean.getSegundaSelecao())) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_PARTIDA_SELECOES_IGUAIS));
        }

        if (validadorMessage.existemErros()) {
            throw new ValidacaoException(validadorMessage);
        }
    }

<<<<<<< HEAD
    @Override
=======
    public Partida obter(Partida filtro) {
        return getDao().obter(filtro);
    }

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Partida> listar(Partida filtro) {
        if (StringUtil.isBlank(filtro.getNomeSelecao())) {
            filtro.setSelecaoList(new HashSet<Selecao>(Constantes.EMPTY));
        } else {
            final List<Selecao> selecaoList = selecaoDao.listar(new Selecao(filtro.getNomeSelecao(), Collections.EMPTY_SET));

            filtro.setSelecaoList(new HashSet<Selecao>(selecaoList));
        }

<<<<<<< HEAD
        filtro.setDataInicial(Util.configurarPeriodoInicial(filtro.getDataInicial()));
        filtro.setDataFinal(Util.configurarPeriodoFinal(filtro.getDataFinal()));

        return getDao().listar(filtro);
    }

    @Override
=======
        return getDao().listar(filtro);
    }

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Selecao> listarSelecao() {
        return selecaoDao.listar(new Selecao());
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public Partida gerarResultado(Partida partida) {
        partida = obter(partida);

        partida.setPlacarPrimeiraSelecao(DesempenhoAtletaEnum.GOL.getValor());
        partida.setPlacarSegundaSelecao(DesempenhoAtletaEnum.GOL.getValor());

        atualizar(partida);

        final List<DesempenhoAtleta> desempenhoAtletaList = DesempenhoAtletaEnum.createDesempenhoAtleta(partida);

        for (DesempenhoAtleta desempenhoAtleta : desempenhoAtletaList) {
            desempenhoAtletaDao.salvar(desempenhoAtleta);
        }

        return partida;
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida, Selecao selecao) {
        return desempenhoAtletaDao.listar(new DesempenhoAtleta(null, selecao, partida));
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void criarComentario(Comentario comentario) {
        final boolean hasComentario = StringUtil.isNotBlank(comentario.getComentario());
        final boolean hasPartida = comentario.getPartida() != null && comentario.getPartida().getId() != null;
        final boolean hasApostador = comentario.getApostador() != null && comentario.getApostador().getId() != null;
<<<<<<< HEAD

        if (hasComentario && hasPartida && hasApostador) {
            comentario.setDataHora(new Date());
            comentario.setComentario(comentario.getComentario().trim());

            comentarioDao.salvar(comentario);
        }
    }

    @Override
=======
        
        if (hasComentario && hasPartida && hasApostador) {
            comentario.setDataHora(new Date());
            comentario.setComentario(comentario.getComentario().trim());
            
            comentarioDao.salvar(comentario);
        }
    }
    
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Comentario> listarComentario(Partida partida) {
        return comentarioDao.listar(new Comentario(null, null, partida));
    }

<<<<<<< HEAD
    @Override
    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida) {
        partida.setDataInicial(Util.configurarPeriodoInicial(partida.getDataInicial()));
        partida.setDataFinal(Util.configurarPeriodoFinal(partida.getDataFinal()));

        final List<Partida> partidaList = listar(partida);

        return desempenhoAtletaDao.listar(partidaList.toArray(new Partida[]{}));
    }

    @Override
    public List<ApostadorEscalacao> listarApostadorEscalacao(Partida partida, Apostador apostador) {
        partida.setDataInicial(Util.configurarPeriodoInicial(partida.getDataInicial()));
        partida.setDataFinal(Util.configurarPeriodoFinal(partida.getDataFinal()));

        final List<Partida> partidaList = listar(partida);

        return apostadorEscalacaoDao.listar(apostador, partidaList.toArray(new Partida[]{}));
    }

    @Override
    public Map<Selecao, Ranking> obterChartSeriesOficial(Partida partida) {
        final List<DesempenhoAtleta> desempenhoAtletaList = listarDesempenhoAtleta(partida);
        final Map<Selecao, Ranking> rankingMap = new HashMap<Selecao, Ranking>(Constantes.EMPTY);

        Ranking ranking;

        for (DesempenhoAtleta desempenhoAtleta : desempenhoAtletaList) {
            ranking = rankingMap.get(desempenhoAtleta.getSelecao());

            if (ranking == null) {
                ranking = PontuacaoEnum.createRanking(desempenhoAtleta.getSelecao(), Arrays.asList(desempenhoAtleta));
            } else {
                ranking = PontuacaoEnum.somar(ranking, desempenhoAtleta);
            }

            rankingMap.put(desempenhoAtleta.getSelecao(), ranking);
        }

        return rankingMap;
    }

    @Override
    public Map<Selecao, Ranking> obterChartSeriesApostador(Partida partida) {
        final List<DesempenhoAtleta> desempenhoAtletaList = listarDesempenhoAtleta(partida);
        final List<ApostadorEscalacao> apostadorEscalacaoList = listarApostadorEscalacao(partida, Util.recuperarUsarioLogado());
        final Map<Selecao, Ranking> rankingMap = new HashMap<Selecao, Ranking>(Constantes.EMPTY);
        final Map<Partida, Set<Atleta>> escalacaoApostadorMap = new HashMap<Partida, Set<Atleta>>(Constantes.EMPTY);

        Ranking ranking;
        Set<Atleta> atletaList;

        for (ApostadorEscalacao apostadorEscalacao : apostadorEscalacaoList) {
            for (Atleta atleta : apostadorEscalacao.getPartida().getPrimeiraSelecao().getAtletaList()) {
                if (apostadorEscalacao.getAtletaList().contains(atleta)) {
                    atletaList = escalacaoApostadorMap.get(apostadorEscalacao.getPartida());

                    if (atletaList == null) {
                        atletaList = new HashSet<Atleta>();
                    }

                    atletaList.add(atleta);

                    escalacaoApostadorMap.put(apostadorEscalacao.getPartida(), atletaList);
                }
            }

            for (Atleta atleta : apostadorEscalacao.getPartida().getSegundaSelecao().getAtletaList()) {
                if (apostadorEscalacao.getAtletaList().contains(atleta)) {
                    atletaList = escalacaoApostadorMap.get(apostadorEscalacao.getPartida());

                    if (atletaList == null) {
                        atletaList = new HashSet<Atleta>();
                    }

                    atletaList.add(atleta);

                    escalacaoApostadorMap.put(apostadorEscalacao.getPartida(), atletaList);
                }
            }
        }

        for (DesempenhoAtleta desempenhoAtleta : desempenhoAtletaList) {
            atletaList = escalacaoApostadorMap.get(desempenhoAtleta.getPartida());

            if (desempenhoAtleta.getNumGols() > 0 && atletaList != null && atletaList.contains(desempenhoAtleta.getAtleta())) {
                ranking = rankingMap.get(desempenhoAtleta.getSelecao());

                if (ranking == null) {
                    ranking = PontuacaoEnum.createRanking(desempenhoAtleta.getSelecao(), Arrays.asList(desempenhoAtleta));
                } else {
                    ranking = PontuacaoEnum.somar(ranking, desempenhoAtleta);
                }

                rankingMap.put(desempenhoAtleta.getSelecao(), ranking);
            }
        }

        return rankingMap;
    }

=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
