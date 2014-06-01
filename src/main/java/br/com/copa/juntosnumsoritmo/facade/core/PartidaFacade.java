package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.dao.IComentarioDao;
import br.com.copa.juntosnumsoritmo.dao.IDesempenhoAtletaDao;
import br.com.copa.juntosnumsoritmo.dao.ISelecaoDao;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IPartidaFacade;
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtletaEnum;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    public Partida obter(Partida filtro) {
        return getDao().obter(filtro);
    }

    public List<Partida> listar(Partida filtro) {
        if (StringUtil.isBlank(filtro.getNomeSelecao())) {
            filtro.setSelecaoList(new HashSet<Selecao>(Constantes.EMPTY));
        } else {
            final List<Selecao> selecaoList = selecaoDao.listar(new Selecao(filtro.getNomeSelecao(), Collections.EMPTY_SET));

            filtro.setSelecaoList(new HashSet<Selecao>(selecaoList));
        }

        return getDao().listar(filtro);
    }

    public List<Selecao> listarSelecao() {
        return selecaoDao.listar(new Selecao());
    }

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

    public List<DesempenhoAtleta> listarDesempenhoAtleta(Partida partida, Selecao selecao) {
        return desempenhoAtletaDao.listar(new DesempenhoAtleta(null, selecao, partida));
    }

    public void criarComentario(Comentario comentario) {
        final boolean hasComentario = StringUtil.isNotBlank(comentario.getComentario());
        final boolean hasPartida = comentario.getPartida() != null && comentario.getPartida().getId() != null;
        final boolean hasApostador = comentario.getApostador() != null && comentario.getApostador().getId() != null;
        
        if (hasComentario && hasPartida && hasApostador) {
            comentario.setDataHora(new Date());
            comentario.setComentario(comentario.getComentario().trim());
            
            comentarioDao.salvar(comentario);
        }
    }
    
    public List<Comentario> listarComentario(Partida partida) {
        return comentarioDao.listar(new Comentario(null, null, partida));
    }

}
