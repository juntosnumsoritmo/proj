package br.com.copa.juntosnumsoritmo.dao.core;

import br.com.copa.juntosnumsoritmo.dao.IAbstractDao;
import br.com.copa.juntosnumsoritmo.exception.SequenceException;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import br.com.copa.juntosnumsoritmo.model.SequenceId;
import java.lang.reflect.ParameterizedType;
import java.util.List;
=======
import br.com.copa.juntosnumsoritmo.model.SequenceId;
import java.io.Serializable;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

<<<<<<< HEAD
public abstract class AbstractDao<T extends AbstractDocument> implements IAbstractDao<T> {
=======
public abstract class AbstractDao<T extends Serializable> implements IAbstractDao<T> {
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19

    @Autowired
    protected MongoTemplate mongoTemplate;

<<<<<<< HEAD
    private final Class<T> type;

    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
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

<<<<<<< HEAD
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

=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
}
