package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.exception.RegraNegocioException;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IApostadorFacade;
import br.com.copa.juntosnumsoritmo.facade.ILoginFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import static br.com.copa.juntosnumsoritmo.util.StringUtil.isBlank;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginFacade implements ILoginFacade {

    @Autowired
    private IApostadorFacade apostadorFacade;

    @Override
    public Apostador verificarUsuario(Apostador usuario) {
        Apostador retorno = null;

        if (usuario != null) {
            final ValidadorMessage errosValidacao = new ValidadorMessage();

            if (isBlank(usuario.getLogin())) {
                errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_LOGIN_VAZIO));
            }

            if (isBlank(usuario.getSenha())) {
                errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_SENHA_VAZIO));
            }

            if (errosValidacao.existemErros()) {
                throw new ValidacaoException(errosValidacao);
            }
            
            retorno = apostadorFacade.verificarLoginUsuario(usuario);
            
            if (retorno == null) {
                throw new RegraNegocioException(Constantes.MSG_USUARIO_INVALIDO);
            }
        }

        return retorno;
    }

    @Override
    public void salvar(Apostador usuario) {
        apostadorFacade.salvar(usuario);
    }
    
}
