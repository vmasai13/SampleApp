/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.pmo.persistence;

import java.util.Date;

/**
 * Any Mongo DB record which is dated.
 * 
 * @author 338286
 */
public abstract class AbstractDatedDBRecord extends AbstractDBRecord {

    protected Date date;

    /**
     * Zero argument constructor. Sets the date of the record to now.
     */
    public AbstractDatedDBRecord() {
        super();
        this.date = new Date();
    }

    /**
     * All argument constructor.
     * 
     * @param date the date of creation of this log record.
     */
    public AbstractDatedDBRecord(Date date) {
        super();
        this.date = date;
    }
    
	/**
     * Gets the date of creation of this log record.
     * 
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of creation of this log record.
     * 
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
