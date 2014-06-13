package br.com.copa.juntosnumsoritmo.facade.core;

import static br.com.copa.juntosnumsoritmo.util.StringUtil.isBlank;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import org.springframework.stereotype.Service;

import br.com.copa.juntosnumsoritmo.dao.IApostadorDao;
import br.com.copa.juntosnumsoritmo.exception.RegraNegocioException;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IApostadorFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;

@Service
public class ApostadorFacade extends AbstractFacade<Apostador> implements IApostadorFacade {

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void salvar(Apostador bean) {
        validar(bean);

        final Apostador apostador = getApostadorDao().obterPeloLogin(bean.getLogin());

        if (apostador != null) {
            throw new RegraNegocioException(Constantes.MSG_APOSTADOR_EXISTENTE);
        }

        getDao().salvar(trim(bean));
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void atualizar(Apostador bean) {
        validar(bean);

        final Apostador apostador = getApostadorDao().obterPeloLogin(bean.getLogin());

        if (apostador != null && apostador.getId() != null && !apostador.getId().equals(bean.getId())) {
            throw new RegraNegocioException(Constantes.MSG_APOSTADOR_EXISTENTE);
        }

        getDao().atualizar(trim(bean));
    }

<<<<<<< HEAD
    @Override
=======
    public void remover(Apostador bean) {
        getDao().remover(bean);
    }

    public Apostador obter(Apostador filtro) {
        return getDao().obter(filtro);
    }

    public List<Apostador> listar(Apostador filtro) {
        return getDao().listar(filtro);
    }

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void validar(Apostador apostador) {
        final ValidadorMessage errosValidacao = new ValidadorMessage();

        if (isBlank(apostador.getNome())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_NOME_VAZIO));
        }

        if (isBlank(apostador.getLogin())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_LOGIN_VAZIO));
        }

        if (apostador.getId() == null && isBlank(apostador.getSenha())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_SENHA_VAZIO));
        }

        if (errosValidacao.existemErros()) {
            throw new ValidacaoException(errosValidacao);
        }
    }

    private Apostador trim(Apostador apostador) {
        apostador.setNome(apostador.getNome().trim());
        apostador.setLogin(apostador.getLogin().trim());
        
        return apostador;
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public Apostador verificarLoginUsuario(Apostador usuario) {
        return getApostadorDao().verificarLoginUsuario(usuario);
    }

    public IApostadorDao getApostadorDao() {
        return (IApostadorDao) getDao();
    }

}
