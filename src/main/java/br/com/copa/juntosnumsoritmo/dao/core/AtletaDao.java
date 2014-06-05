package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IAtletaDao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AtletaDao extends AbstractDao<Atleta> implements IAtletaDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_ATLETA;
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
