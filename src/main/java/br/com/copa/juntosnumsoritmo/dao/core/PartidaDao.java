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

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_PARTIDA;
    }

    @Override
    public List<Partida> listar(Partida filtro) {
        final Query query = new Query();

        if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
            query.addCriteria(Criteria.where("dataHora").gte(filtro.getDataInicial()).lte(filtro.getDataFinal()));
        }

        if (StringUtil.isNotBlank(filtro.getNomeSelecao()) && !filtro.getSelecaoList().isEmpty()) {
            final Set<Long> selecaoIdList = new HashSet<Long>(Constantes.EMPTY);

            for (Selecao selecao : filtro.getSelecaoList()) {
                selecaoIdList.add(selecao.getId());
            }

            final Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("primeiraSelecao.$id").in(selecaoIdList),
                    Criteria.where("segundaSelecao.$id").in(selecaoIdList));

            query.addCriteria(criteria);
        }

        query.with(new Sort(Sort.Direction.DESC, "dataHora"));

        return getMongoTemplate().find(query, Partida.class);
    }

    @Override
    public List<Partida> listarPartidaNaoRealizadas() {
        final Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("placarPrimeiraSelecao").exists(false), 
                             Criteria.where("placarSegundaSelecao").exists(false));
        
        final Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "dataHora"));

        return getMongoTemplate().find(query, Partida.class);
    }

}
