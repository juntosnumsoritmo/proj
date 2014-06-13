package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.dao.IAtletaDao;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.ISelecaoFacade;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelecaoFacade extends AbstractFacade<Selecao> implements ISelecaoFacade {
    
    @Autowired
    private IAtletaDao atletaDao;

    @Override
<<<<<<< HEAD
=======
    public void salvar(Selecao bean) {
        validar(bean);

        getDao().salvar(bean);
    }

    @Override
    public void atualizar(Selecao bean) {
        validar(bean);

        getDao().atualizar(bean);
    }

    @Override
    public void remover(Selecao bean) {
        getDao().remover(bean);
    }

    @Override
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void validar(Selecao bean) {
        final ValidadorMessage errosValidacao = new ValidadorMessage();

        if (StringUtil.isBlank(bean.getNome())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_SELECAO_NOME_VAZIO));
        }
<<<<<<< HEAD
        
        if (bean.getAtletaList().size() > Constantes.MAXIMO_ATLETA_SELECAO) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_SELECAO_ATLETAS_EXCEDIDO));
        }
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

        if (errosValidacao.existemErros()) {
            throw new ValidacaoException(errosValidacao);
        }
    }

    @Override
<<<<<<< HEAD
=======
    public Selecao obter(Selecao filtro) {
        return getDao().obter(filtro);
    }

    @Override
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Selecao> listar(Selecao filtro) {
        if (StringUtil.isBlank(filtro.getNomeAtleta())) {
            filtro.setAtletaList(new HashSet<Atleta>(Constantes.EMPTY));
        } else {
            final Atleta atleta = new Atleta();
            atleta.setNome(filtro.getNomeAtleta());
            
            filtro.setAtletaList(new HashSet<Atleta>(atletaDao.listar(atleta)));
        }

        return getDao().listar(filtro);
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Atleta> listarAtleta() {
        return atletaDao.listar(new Atleta());
    }

}
