package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IAbstractDao;
import br.com.copa.juntosnumsoritmo.exception.SequenceException;
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import br.com.copa.juntosnumsoritmo.model.SequenceId;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class AbstractDao<T extends AbstractDocument> implements IAbstractDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> type;

    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract String getNomeColecao();

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public Number getNextSequenceId() {
        final Query query = new Query(Criteria.where("_id").is(getNomeColecao()));

        final Update update = new Update();
        update.inc("seq", 1);

        final FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SequenceId seqId
                = getMongoTemplate().findAndModify(query, update, options, SequenceId.class);

        if (seqId == null) {
            getMongoTemplate().insert(new SequenceId(getNomeColecao(), 1));

            seqId = getMongoTemplate().findOne(query, SequenceId.class);
        }

        if (seqId == null) {
            throw new SequenceException("Não foi possível gerar o ID : " + getNomeColecao());
        }

        return seqId.getSeq();
    }

    @Override
    public void salvar(T bean) {
        bean.setId(getNextSequenceId().longValue());

        getMongoTemplate().insert(bean);
    }

    @Override
    public void atualizar(T bean) {
        getMongoTemplate().save(bean);
    }

    @Override
    public T obter(T filtro) {
        return getMongoTemplate().findById(filtro.getId(), type);
    }

    @Override
    public void remover(T bean) {
        getMongoTemplate().remove(bean);
    }

    @Override
    public List<T> listarTodos() {
        return getMongoTemplate().findAll(type);
    }

}
