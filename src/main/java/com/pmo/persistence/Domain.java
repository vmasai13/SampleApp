/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */
package com.pmo.persistence;

/**
 * The top level domain interface. It includes the basic contract that any implementing domain class should implement.
 * 
 * @author 338286
 */
public interface Domain {

    /**
     * Gets the primary key
     * 
     * @return primary key
     */
    public String getId();

    /**
     * Sets the primary key
     * 
     * @param id primary key
     */
    public void setId(String id);
}
