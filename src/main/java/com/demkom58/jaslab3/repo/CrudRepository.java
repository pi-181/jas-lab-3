package com.demkom58.jaslab3.repo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface CrudRepository<ID, VALUE> {
    @NotNull Collection<VALUE> getAll();

    @Nullable VALUE getById(@NotNull ID id);

    boolean removeById(@NotNull ID id);

    void save(@NotNull VALUE value);

    @NotNull String getTableName();
}
