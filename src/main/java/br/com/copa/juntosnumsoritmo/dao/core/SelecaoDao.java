package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.ISelecaoDao;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.util.Util;
=======
import java.util.ArrayList;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
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
<<<<<<< HEAD
=======
    public void salvar(Selecao bean) {
        bean.setId(getNextSequenceId().longValue());

        getMongoTemplate().insert(bean);
    }

    @Override
    public void atualizar(Selecao bean) {
        getMongoTemplate().save(bean, getNomeColecao());
    }

    @Override
    public void remover(Selecao bean) {
        getMongoTemplate().remove(bean);
    }

    @Override
    public Selecao obter(Selecao filtro) {
        return getMongoTemplate().findById(filtro.getId(), Selecao.class);
    }

    @Override
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Selecao> listar(Selecao filtro) {
        final Query query = new Query();

        if (StringUtil.isNotBlank(filtro.getNome())) {
            query.addCriteria(Criteria.where("nome").regex(filtro.getNome(), "i"));
        }

        if (!filtro.getAtletaList().isEmpty()) {
<<<<<<< HEAD
            final List<Long> atletaIdList = Util.obterAbstractDocumentIdList(filtro.getAtletaList());

            query.addCriteria(Criteria.where("atletas.$id").in(atletaIdList));
        }

=======
            final List<Long> atletaIdList = new ArrayList<Long>(Constantes.EMPTY);

            for (Atleta atleta : filtro.getAtletaList()) {
                atletaIdList.add(atleta.getId());
            }

            query.addCriteria(Criteria.where("atletas.$id").in(atletaIdList));
        }
        
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        query.with(new Sort(Sort.Direction.ASC, "nome"));

        return getMongoTemplate().find(query, Selecao.class);
    }

<<<<<<< HEAD
    @Override
    public List<Atleta> listarAtleta(Selecao selecao) {
        selecao = getMongoTemplate().findById(selecao.getId(), Selecao.class);

        final List<Long> atletaIdList = Util.obterAbstractDocumentIdList(selecao.getAtletaList());
        
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").in(atletaIdList));

        return getMongoTemplate().find(query, Atleta.class);
    }

=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
