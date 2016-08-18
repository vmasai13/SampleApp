/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.pmo.persistence;

import org.springframework.data.annotation.Id;

/**
 * Top level abstraction for a Mongo DB record.
 * 
 * @author 338286
 */
public abstract class AbstractDBRecord implements Domain {

    @Id
    protected String id;

    /**
     * Zero argument constructor.
     */
    public AbstractDBRecord() {
        super();
    }

    /**
     * All argument constructor.
     * 
     * @param id the primary key
     */
    public AbstractDBRecord(String id) {
        super();
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

}
