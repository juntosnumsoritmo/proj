package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.facade.ILoginFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.Util;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class LoginControle extends AbstractControle {
    
    private static final long serialVersionUID = -3688210987574830923L;

    private Apostador usuario;

    @Autowired
    private ILoginFacade loginFacade;

    @PostConstruct
    public void init() {
        usuario = new Apostador();
    }

    public String login() {
        String retorno = null;

        final Apostador usuarioLogado = loginFacade.verificarUsuario(usuario);

        if (usuarioLogado == null) {
            addMessageError(Constantes.MSG_USUARIO_INVALIDO);
        } else {
            Util.getSession().setAttribute(Constantes.USUARIO_LOGADO, usuarioLogado);

            retorno = Constantes.PAGINA_INICIAL;
        }

        return retorno;
    }

    public String salvarNovoUsuario() {
        loginFacade.salvar(usuario);

        return login();
    }

    public String sair() {
        Util.getSession().invalidate();

        return Constantes.PAGINA_LOGIN;
    }

    public void setUsuario(Apostador usuario) {
        this.usuario = usuario;
    }

    public Apostador getUsuario() {
        return usuario;
    }

}
