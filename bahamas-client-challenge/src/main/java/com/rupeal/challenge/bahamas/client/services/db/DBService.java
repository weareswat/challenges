package com.rupeal.challenge.bahamas.client.services.db;

import javax.persistence.EntityManager;

public abstract class DBService<ID, TYPE> {

    private final EntityManager em;

    protected DBService(EntityManager em) {
        this.em = em;
    }

    abstract Class<TYPE> getEntityClass();

    public TYPE find(ID id){
        return em.find(getEntityClass(), id);
    }

    public EntityManager getEm() {
        return em;
    }
}
