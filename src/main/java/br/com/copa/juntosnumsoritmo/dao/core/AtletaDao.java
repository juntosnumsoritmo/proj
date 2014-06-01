package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IAtletaDao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class AtletaDao extends AbstractDao<Atleta> implements IAtletaDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_ATLETA;
    }

    @Override
    public void salvar(Atleta atleta) {
        atleta.setId(getNextSequenceId().longValue());

        getMongoTemplate().insert(atleta);
    }

    @Override
    public void atualizar(Atleta bean) {
        final Query query = new Query(Criteria.where("id").is(bean.getId()));
        
        final Update update = new Update();
        update.set("nome", bean.getNome());
        update.set("idade", bean.getIdade());
        update.set("pais", bean.getPais());
        update.set("pontuacao", bean.getPontuacao());
        update.set("posicao", bean.getPosicao());
        
        getMongoTemplate().updateFirst(query, update, Atleta.class);
    }

    @Override
    public void remover(Atleta bean) {
        getMongoTemplate().remove(new Query(Criteria.where("id").is(bean.getId())), Atleta.class);
        
    }

    @Override
    public Atleta obter(Atleta filtro) {
        return getMongoTemplate().findById(filtro.getId(), Atleta.class);
    }

    @Override
    public List<Atleta> listar(Atleta filtro) {
        final Query query = new Query();

        if (StringUtil.isNotBlank(filtro.getNome())) {
            query.addCriteria(Criteria.where("nome").regex(filtro.getNome(), "i"));
        }
        
        if (StringUtil.isNotBlank(filtro.getPais())) {
            query.addCriteria(Criteria.where("pais").regex(filtro.getPais(), "i"));
        }
        
        query.with(new Sort(Sort.Direction.ASC, "nome"));
        
        return getMongoTemplate().find(query, Atleta.class);
    }

}
