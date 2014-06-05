package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IApostadorEscalacaoDao;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.ApostadorEscalacao;
import br.com.copa.juntosnumsoritmo.model.Partida;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.util.StringUtil;
import br.com.copa.juntosnumsoritmo.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ApostadorEscalacaoDao extends AbstractDao<ApostadorEscalacao> implements IApostadorEscalacaoDao {

    @Override
    public String getNomeColecao() {
        return Constantes.NOME_COLECAO_APOSTADOR_ESCALACAO;
    }

    @Override
    public List<ApostadorEscalacao> listar(ApostadorEscalacao filtro) {
        final List<ApostadorEscalacao> retorno = new ArrayList<ApostadorEscalacao>(Constantes.EMPTY);
        final boolean hasApostador = filtro.getApostador() != null && filtro.getApostador().getId() != null;
        final boolean hasPartida = filtro.getPartida() != null && filtro.getPartida().getId() != null;
        final boolean hasPartidaList = filtro.getPartidaFiltroList() != null && filtro.getPartidaFiltroList().size() > Constantes.EMPTY;
        final boolean hasNome = StringUtil.isNotBlank(filtro.getNome());
        final boolean hasData = filtro.getDataHora() != null;
        final Query query = new Query();
        
        if (hasNome) {
            query.addCriteria(Criteria.where("nome").regex(filtro.getNome(), "i"));
        }
        
        if (hasData) {
            query.addCriteria(Criteria.where("dataHora").is(filtro.getDataHora()));
        }
        
        if (hasApostador) {
            query.addCriteria(Criteria.where("apostador.$id").is(filtro.getApostador().getId()));
        }
        
        if (hasPartida) {
            query.addCriteria(Criteria.where("partida.$id").is(filtro.getPartida().getId()));
        }
        
        if (hasPartidaList) {
            final List<Long> partidaIdList = Util.obterAbstractDocumentIdList(filtro.getPartidaFiltroList());
            
            query.addCriteria(Criteria.where("partida.$id").in(partidaIdList));
        }
        
        if (hasApostador || hasPartida || hasData || hasNome) {
            query.with(new Sort(Sort.Direction.DESC, "dataHora"));
            
            retorno.addAll(getMongoTemplate().find(query, ApostadorEscalacao.class));
        }

        return retorno;
    }
    
    @Override
    public List<ApostadorEscalacao> listar(Apostador apostador, Partida... partidaList) {
        final List<ApostadorEscalacao> retorno = new ArrayList<ApostadorEscalacao>(Constantes.EMPTY);
        final List<Long> partidaIdList = Util.obterAbstractDocumentIdList(partidaList);
        
        if (partidaIdList.size() > 0) {
            Criteria criteria = new Criteria();
            criteria.andOperator(Criteria.where("partida.$id").in(partidaIdList),
                                 Criteria.where("apostador.$id").is(apostador.getId()));
            
            retorno.addAll(getMongoTemplate().find(new Query(criteria), ApostadorEscalacao.class));
        }
        
        return retorno;
    }

}
