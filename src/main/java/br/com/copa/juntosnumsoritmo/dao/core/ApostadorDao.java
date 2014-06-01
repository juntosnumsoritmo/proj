
package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IApostadorDao;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ApostadorDao extends AbstractDao<Apostador> implements IApostadorDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_APOSTADOR;
    }
    
    public void salvar(Apostador bean) {
        bean.setId(getNextSequenceId().longValue());
        
        getMongoTemplate().insert(bean);
    }

    public void atualizar(Apostador bean) {
        final Query query = new Query(Criteria.where("id").is(bean.getId()));
        
        final Update update = new Update();
        update.set("nome", bean.getNome());
        update.set("login", bean.getLogin());
        update.set("pontos", bean.getPontos());
        update.set("admin", bean.isAdmin());
        
        if (StringUtil.isNotBlank(bean.getSenha())) {
            update.set("senha", bean.getSenha());
        }
        
        getMongoTemplate().updateFirst(query, update, Apostador.class);
    }

    public void remover(Apostador bean) {
        getMongoTemplate().remove(new Query(Criteria.where("id").is(bean.getId())), Apostador.class);
    }
    
    public Apostador obter(Apostador bean) {
        return getMongoTemplate().findById(bean.getId(), Apostador.class);
    }

    public List<Apostador> listar(Apostador filtro) {
        final Query query = new Query();
        
        if (StringUtil.isNotBlank(filtro.getLogin())) {
            query.addCriteria(Criteria.where("login").regex(filtro.getLogin(), "i"));
        }
        
        if (StringUtil.isNotBlank(filtro.getNome())) {
            query.addCriteria(Criteria.where("nome").regex(filtro.getNome(), "i"));
        }
        
        return getMongoTemplate().find(query, Apostador.class);
    }

    public Apostador verificarLoginUsuario(Apostador usuario) {
        Apostador retorno = null;
        
        final Criteria criteria = Criteria.where("login").is(usuario.getLogin()).and("senha").is(usuario.getSenha());
        final Query query = new Query(criteria);
        final List<Apostador> usuarioList = getMongoTemplate().find(query, Apostador.class);
        
        if (usuarioList != null && !usuarioList.isEmpty()) {
            retorno = usuarioList.get(Constantes.EMPTY);
        }
        
        return retorno;
    }

    public Apostador obterPeloLogin(String loginUsuario) {
        final StringBuilder pattern = new StringBuilder();
        pattern.append("^").append(loginUsuario.trim()).append("$");
        
        final Query query = new Query(Criteria.where("login").regex(pattern.toString(), "i"));
        final List<Apostador> apostadorList = getMongoTemplate().find(query, Apostador.class);
        Apostador retorno = null;
        
        if (!apostadorList.isEmpty()) {
            retorno = apostadorList.get(Constantes.EMPTY);
        }
        
        return retorno;
    }
    
}
