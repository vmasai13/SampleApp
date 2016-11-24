/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.pmo.persistence;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDBPersistenceService implements PersistenceService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * {@inheritDoc} This method checks whether the _id filed is undefined </br> for this Domain object. If so then it does an insert and returns the inserted object.</br> If not then it logs an error message and returns
     * null.
     */
    @Override
    public Domain insert(Domain domain) {

        if (StringUtils.isBlank(domain.getId())) {
            System.out.println("Persisting: {" + domain.toString() + "}");
            mongoTemplate.insert(domain);
            return domain;
        }
        else {
        	System.out.println("Cannot insert since primary key {" + domain.getId() + "} is defined for {" + domain.toString() + "}, should use the Update API");
            return null;
        }
    }

    /**
     * Checks whether the collection identified by class <code>clazz</code> exists or not.
     * 
     * @param clazz class name of the collection to check.
     * @return true if the collection exists, false otherwise.
     */
    public boolean checkWhetherCollectionExists(Class<?> clazz) {
        return mongoTemplate.collectionExists(clazz);
    }

    /**
     * {@inheritDoc} This method checks whether the _id field is defined </br> for this Domain object. If so then it does an update and returns the updated object.</br> If not then it logs an error message and returns
     * null.
     */
    @Override
    public Domain update(Domain domain) {
        if (domain.getId() != null && !domain.getId().isEmpty()) {
        	System.out.println("Updating: {" + domain.toString() + "}");
            mongoTemplate.save(domain);
            return domain;
        }
        else {
        	System.out.println("Cannot update since primary key not defined for {" + domain.toString() + "}, should use the Insert API");
            return null;
        }
    }

    /**
     * 
     * {@inheritDoc} This method checks whether the _id filed is defined </br> for this Domain object. If so then it removes the object from the DB.</br> If not, then it logs an error message and does nothing else.
     */
    @Override
    public void delete(Domain domain) {
        if (domain.getId() != null && !domain.getId().isEmpty()) {
        	System.out.println("Deleting: {" + domain.toString() + "}");
            mongoTemplate.remove(domain);
        }
        else {
        	System.out.println("Could not delete since the object does not have a primary key set: {" + domain.toString() + "}");
        }
    }
}
