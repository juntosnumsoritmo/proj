package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IComentarioDao;
import br.com.copa.juntosnumsoritmo.model.Comentario;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioDao extends AbstractDao<Comentario> implements IComentarioDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_COMENTARIO;
    }

    @Override
    public List<Comentario> listar(Comentario filtro) {
        final Query query = new Query();

        if (filtro.getApostador() != null && filtro.getApostador().getId() != null) {
            query.addCriteria(Criteria.where("apostador.$id").is(filtro.getApostador().getId()));
        }

        if (filtro.getPartida() != null && filtro.getPartida().getId() != null) {
            query.addCriteria(Criteria.where("partida.$id").is(filtro.getPartida().getId()));
        }

        if (StringUtil.isNotBlank(filtro.getComentario())) {
            query.addCriteria(Criteria.where("comentario").regex(filtro.getComentario(), "i"));
        }
        
        query.with(new Sort(Sort.Direction.DESC, "dataHora"));

        return getMongoTemplate().find(query, Comentario.class);
    }

}
