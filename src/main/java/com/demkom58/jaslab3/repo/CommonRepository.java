package com.demkom58.jaslab3.repo;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;

public class CommonRepository<K, V> implements CrudRepository<K, V> {
    private final EntityManager entityManager;

    private final Query selectAll;
    private final Query selectById;
    private final Query deleteById;

    private final String tableName;

    public CommonRepository(EntityManager entityManager, String tableName, String idColumnName) {
        this.entityManager = entityManager;
        this.tableName = tableName;

        this.selectAll = entityManager.createQuery("FROM " + tableName);
        this.selectById = entityManager.createQuery("FROM " + tableName + " WHERE " + idColumnName + " = :id");
        this.deleteById = entityManager.createQuery("DELETE FROM " + tableName + " WHERE " + idColumnName + " = :id");
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull Collection<V> getAll() {
        return selectAll.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable V getById(@NotNull K key) {
        return (V) selectById.setParameter("id", key).getSingleResult();
    }

    @Override
    public boolean removeById(@NotNull K key) {
        final EntityTransaction transaction = entityManager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            result = deleteById.setParameter("id", key).executeUpdate() != 0;
        } finally {
            transaction.commit();
        }

        return result;
    }

    @Override
    public void save(@NotNull V value) {
        final EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(value);
        } finally {
            transaction.commit();
        }
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
