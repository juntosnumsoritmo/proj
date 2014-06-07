package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.dao.IPartidaDao;
import br.com.copa.juntosnumsoritmo.dao.ISelecaoDao;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IApostadorEscalacaoFacade;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.util.Util;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApostadorEscalacaoFacade extends AbstractFacade<ApostadorEscalacao> implements IApostadorEscalacaoFacade {

    @Autowired
    private ISelecaoDao selecaoDao;

    @Autowired
    private IPartidaDao partidaDao;

    @Override
    public void validar(ApostadorEscalacao bean) {
        final ValidadorMessage validadorMessage = new ValidadorMessage();

        if (StringUtil.isBlank(bean.getNome())) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_NOME_VAZIO));
        }

        if (bean.getDataHora() == null) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_DATA_VAZIA));
        }

        if (bean.getPartida() == null) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_PARTIDA_VAZIA));
        }

        if (bean.getApostador() == null) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_APOSTADOR_VAZIO));
        }

        if (bean.getAtletaList() == null || bean.getAtletaList().isEmpty()) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_ATLETA_VAZIO));
        }

        if (bean.getAtletaList().size() > Constantes.MAXIMO_ATLETA_SELECAO) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_ATLETA_EXCEDIDO));
        }

        if (validadorMessage.existemErros()) {
            throw new ValidacaoException(validadorMessage);
        }

        final Partida partida = partidaDao.obter(bean.getPartida());

        if (StringUtil.isNotBlank(partida.getPlacar())) {
            validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_SEM_PARTIDA_ABERTO));
            throw new ValidacaoException(validadorMessage);
        }

        final ApostadorEscalacao filtro = new ApostadorEscalacao(null, null, bean.getApostador(), bean.getPartida());
        final List<ApostadorEscalacao> apostadorEscalacaoList = listar(filtro);

        if (apostadorEscalacaoList.size() > 0) {
            final ApostadorEscalacao escalacao = apostadorEscalacaoList.get(0);

            if (!escalacao.getId().equals(bean.getId())) {
                validadorMessage.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_ESCALACAO_EXISTENTE));
                throw new ValidacaoException(validadorMessage);
            }
        }
    }

    @Override
    public List<ApostadorEscalacao> listar(ApostadorEscalacao filtro) {
        final boolean hasPeriodo = filtro.getDataInicial() != null && filtro.getDataFinal() != null;
        final boolean hasNomeSelecao = StringUtil.isNotBlank(filtro.getNomeSelecao());
        final Partida partida = new Partida();

        if (hasNomeSelecao) {
            final List<Selecao> selecaoList = selecaoDao.listar(new Selecao(filtro.getNomeSelecao(), Collections.EMPTY_SET));

            partida.setSelecaoList(new HashSet<Selecao>(selecaoList));
            partida.setNomeSelecao(filtro.getNomeSelecao());
        }

        if (hasPeriodo) {
            partida.setDataInicial(Util.configurarPeriodoInicial(filtro.getDataInicial()));
            partida.setDataFinal(Util.configurarPeriodoFinal(filtro.getDataFinal()));
        }

        if (hasPeriodo || hasNomeSelecao) {
            final List<Partida> partidaList = partidaDao.listar(partida);

            if (partidaList.isEmpty()) {
                partidaList.add(new Partida(-1L));
            }

            filtro.setPartidaFiltroList(partidaList);
        }

        return super.listar(filtro);
    }

    @Override
    public List<Atleta> listarAtleta(Selecao selecao) {
        return selecaoDao.listarAtleta(selecao);
    }

    @Override
    public List<Partida> listarPartidaNaoRealizadas() {
        return partidaDao.listarPartidaNaoRealizadas();
    }

}
