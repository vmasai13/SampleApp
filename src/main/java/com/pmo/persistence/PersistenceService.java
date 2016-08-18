/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.pmo.persistence;


/**
 * Root level persistence service interface encapsulating the contract that all implementing persistence service classes must honour.
 */
public interface PersistenceService {

    /**
     * A generic method to insert the passed domain object into the DB.
     * 
     * @param domain domain object to be inserted to DB.
     * @return the same domain object once it has been inserted (now it has its primary key field set).
     */
    public Domain insert(Domain domain);

    /**
     * A generic method to update an existing domain object in the DB. A domain object already exists in the DB if and only if its primary key field is already set.
     * 
     * @param domain the updated doamin object.
     * @return the domain object after the update operation.
     */
    public Domain update(Domain domain);

    /**
     * A generic method to delete an existing domain object in the DB. A domain object already exists in the DB if and only if its primary key field is already set.
     * 
     * @param domain the domain object to be deleted (must be an existing object in DB with id field set)
     */
    public void delete(Domain domain);

}
