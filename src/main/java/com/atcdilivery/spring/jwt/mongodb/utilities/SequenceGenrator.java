package com.atcdilivery.spring.jwt.mongodb.utilities;

import com.atcdilivery.spring.jwt.mongodb.entity.DatabaseUserSequence;
import com.atcdilivery.spring.jwt.mongodb.entity.DatabasesOrderSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenrator {
    private MongoOperations mongoOperations;

    @Autowired
    public void SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateOrderSequence(String seqName) {
        DatabasesOrderSequence counter = mongoOperations.findAndModify(query(where("orderId").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabasesOrderSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long generateUserSequence(String seqName) {
        DatabaseUserSequence counter = mongoOperations.findAndModify(query(where("userId").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseUserSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
