package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IPartidaDao;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PartidaDao extends AbstractDao<Partida> implements IPartidaDao {

<<<<<<< HEAD
    @Override
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_PARTIDA;
    }

    @Override
<<<<<<< HEAD
=======
    public void salvar(Partida bean) {
        bean.setId(getNextSequenceId().longValue());

        getMongoTemplate().insert(bean);
    }

    @Override
    public void atualizar(Partida bean) {
        getMongoTemplate().save(bean);
    }

    @Override
    public void remover(Partida bean) {
        getMongoTemplate().remove(bean);
    }

    @Override
    public Partida obter(Partida filtro) {
        return getMongoTemplate().findById(filtro.getId(), Partida.class);
    }

    @Override
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    public List<Partida> listar(Partida filtro) {
        final Query query = new Query();

        if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
            query.addCriteria(Criteria.where("dataHora").gte(filtro.getDataInicial()).lte(filtro.getDataFinal()));
        }

        if (StringUtil.isNotBlank(filtro.getNomeSelecao()) && !filtro.getSelecaoList().isEmpty()) {
            final Set<Long> selecaoIdList = new HashSet<Long>(Constantes.EMPTY);
<<<<<<< HEAD

            for (Selecao selecao : filtro.getSelecaoList()) {
                selecaoIdList.add(selecao.getId());
            }

            final Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("primeiraSelecao.$id").in(selecaoIdList),
                    Criteria.where("segundaSelecao.$id").in(selecaoIdList));

=======
            
            for (Selecao selecao : filtro.getSelecaoList()) {
                selecaoIdList.add(selecao.getId());
            }
            
            final Criteria criteria = new Criteria();
            criteria.orOperator( Criteria.where("primeiraSelecao.$id").in(selecaoIdList), 
                                  Criteria.where("segundaSelecao.$id").in(selecaoIdList) );
            
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
            query.addCriteria(criteria);
        }

        query.with(new Sort(Sort.Direction.DESC, "dataHora"));

        return getMongoTemplate().find(query, Partida.class);
    }

<<<<<<< HEAD
    @Override
    public List<Partida> listarPartidaNaoRealizadas() {
        final Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("placarPrimeiraSelecao").exists(false), 
                             Criteria.where("placarSegundaSelecao").exists(false));
        
        final Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "dataHora"));

        return getMongoTemplate().find(query, Partida.class);
    }

=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
