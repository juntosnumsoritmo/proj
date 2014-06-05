package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IDesempenhoAtletaDao;
import br.com.copa.juntosnumsoritmo.model.DesempenhoAtleta;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DesempenhoAtletaDao extends AbstractDao<DesempenhoAtleta> implements IDesempenhoAtletaDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_DESEMPENHO_ATLETA;
    }

    @Override
    public List<DesempenhoAtleta> listar(DesempenhoAtleta filtro) {
        final List<DesempenhoAtleta> retorno = new ArrayList<DesempenhoAtleta>(Constantes.EMPTY);
        final boolean hasAtleta = filtro.getAtleta() != null && filtro.getAtleta().getId() != null;
        final boolean hasPartida = filtro.getPartida() != null && filtro.getPartida().getId() != null;
        final boolean hasSelecao = filtro.getSelecao() != null && filtro.getSelecao().getId() != null;
        final Query query = new Query();

        if (hasAtleta) {
            query.addCriteria(Criteria.where("atleta.$id").is(filtro.getAtleta().getId()));
        }

        if (hasPartida) {
            query.addCriteria(Criteria.where("partida.$id").is(filtro.getPartida().getId()));
        }

        if (hasSelecao) {
            query.addCriteria(Criteria.where("selecao.$id").is(filtro.getSelecao().getId()));
        }

        if (hasAtleta || hasPartida || hasSelecao) {
            retorno.addAll(getMongoTemplate().find(query, DesempenhoAtleta.class));
        }

        return retorno;
    }

    @Override
    public List<DesempenhoAtleta> listar(Partida... partidaList) {
        final List<DesempenhoAtleta> desempenhoAtletaList = new ArrayList<DesempenhoAtleta>(Constantes.EMPTY);
        final List<Long> partidaIdList = Util.obterAbstractDocumentIdList(partidaList);

        if (partidaIdList.size() > 0) {
            final Query query = new Query();
            query.addCriteria(Criteria.where("partida.$id").in(partidaIdList));

            desempenhoAtletaList.addAll(getMongoTemplate().find(query, DesempenhoAtleta.class));
        }

        return desempenhoAtletaList;
    }

}
