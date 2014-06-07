package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.ISelecaoDao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SelecaoDao extends AbstractDao<Selecao> implements ISelecaoDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_SELECAO;
    }

    @Override
    public List<Selecao> listar(Selecao filtro) {
        final Query query = new Query();

        if (StringUtil.isNotBlank(filtro.getNome())) {
            query.addCriteria(Criteria.where("nome").regex(filtro.getNome(), "i"));
        }

        if (!filtro.getAtletaList().isEmpty()) {
            final List<Long> atletaIdList = Util.obterAbstractDocumentIdList(filtro.getAtletaList());

            query.addCriteria(Criteria.where("atletas.$id").in(atletaIdList));
        }

        query.with(new Sort(Sort.Direction.ASC, "nome"));

        return getMongoTemplate().find(query, Selecao.class);
    }

    @Override
    public List<Atleta> listarAtleta(Selecao selecao) {
        selecao = getMongoTemplate().findById(selecao.getId(), Selecao.class);

        final List<Long> atletaIdList = Util.obterAbstractDocumentIdList(selecao.getAtletaList());
        
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").in(atletaIdList));

        return getMongoTemplate().find(query, Atleta.class);
    }

}
