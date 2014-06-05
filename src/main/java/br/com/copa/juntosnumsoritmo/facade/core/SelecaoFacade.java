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
    public void validar(Selecao bean) {
        final ValidadorMessage errosValidacao = new ValidadorMessage();

        if (StringUtil.isBlank(bean.getNome())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_SELECAO_NOME_VAZIO));
        }
        
        if (bean.getAtletaList().size() > Constantes.MAXIMO_ATLETA_SELECAO) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_SELECAO_ATLETAS_EXCEDIDO));
        }

        if (errosValidacao.existemErros()) {
            throw new ValidacaoException(errosValidacao);
        }
    }

    @Override
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

    @Override
    public List<Atleta> listarAtleta() {
        return atletaDao.listar(new Atleta());
    }

}
