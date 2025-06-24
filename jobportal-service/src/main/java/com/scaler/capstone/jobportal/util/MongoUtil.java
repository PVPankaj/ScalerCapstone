package com.scaler.capstone.jobportal.util;

import java.security.SecureRandom;

import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * Utility class for generating sequential IDs using MongoDB.
 * Relies on a Sequence collection to manage atomic counter updates.
 */
@Component
public class MongoUtil {
    private static MongoOperations mongoOperation;

    /**
     * Sets the MongoOperations bean statically.
     * Used by Spring to inject the MongoOperations instance.
     *
     * @param mongoOperation the MongoOperations instance to be set
     */
    @Autowired
    public void setMongoOperation(MongoOperations mongoOperation) {
        MongoUtil.mongoOperation = mongoOperation;
    }

    /**
     * Retrieves the next sequence ID for a given key from the Sequence collection.
     * Increments the existing value atomically.
     *
     * @param key the key for which the sequence ID is needed
     * @return the next incremented sequence ID
     * @throws JobPortalException if sequence generation fails
     */
    public static Long getNextSequenceId(String key) throws JobPortalException {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Sequence seqId = mongoOperation.findAndModify(query, update, options, Sequence.class);
        if (seqId == null) {
            throw new JobPortalException("Unable to get sequence id for key : " + key);
        }

        return seqId.getSeq();
    }
}
