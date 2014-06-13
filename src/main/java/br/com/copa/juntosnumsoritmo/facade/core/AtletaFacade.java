package br.com.copa.juntosnumsoritmo.facade.core;

import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.facade.IAtletaFacade;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import br.com.copa.juntosnumsoritmo.validador.ValidadorMessage;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import org.springframework.stereotype.Service;

@Service
public class AtletaFacade extends AbstractFacade<Atleta> implements IAtletaFacade {

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void salvar(Atleta atleta) {
        validar(atleta);

        getDao().salvar(trim(atleta));
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public void atualizar(Atleta atleta) {
        validar(atleta);

        getDao().atualizar(trim(atleta));
    }

<<<<<<< HEAD
=======
    public void remover(Atleta atleta) {
        getDao().remover(atleta);
    }
    
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    private Atleta trim(Atleta atleta) {
        atleta.setNome(atleta.getNome().trim());
        atleta.setPais(atleta.getPais().trim());
        
        return atleta;
    }

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
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

<<<<<<< HEAD
=======
    public Atleta obter(Atleta filtro) {
        return getDao().obter(filtro);
    }

    public List<Atleta> listar(Atleta filtro) {
        return getDao().listar(filtro);
    }

>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
