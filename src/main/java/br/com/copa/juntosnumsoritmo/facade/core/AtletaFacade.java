package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IAtletaFacade;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
import org.springframework.stereotype.Service;

@Service
public class AtletaFacade extends AbstractFacade<Atleta> implements IAtletaFacade {

    @Override
    public void salvar(Atleta atleta) {
        validar(atleta);

        getDao().salvar(trim(atleta));
    }

    @Override
    public void atualizar(Atleta atleta) {
        validar(atleta);

        getDao().atualizar(trim(atleta));
    }

    private Atleta trim(Atleta atleta) {
        atleta.setNome(atleta.getNome().trim());
        atleta.setPais(atleta.getPais().trim());
        
        return atleta;
    }

    @Override
    public void validar(Atleta atleta) {
        final ValidadorMessage errosValidacao = new ValidadorMessage();
        
        if (StringUtil.isBlank(atleta.getNome())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_APOSTADOR_NOME_VAZIO));
        }
        
        if (StringUtil.isBlank(atleta.getPais())) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_ATLETA_PAIS_VAZIO));
        }
        
        if (atleta.getIdade() == null) {
            errosValidacao.addMessage(new Mensagem(Constantes.MSG_ATLETA_IDADE_VAZIO));
        }

        if (errosValidacao.existemErros()) {
            throw new ValidacaoException(errosValidacao);
        }
    }

}
