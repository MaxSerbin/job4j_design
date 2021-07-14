package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
     mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        T find = findById(id);
        if (find == null) {
            return false;
        } else {
            mem.replace(find.getId(), model);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        T find = findById(id);
        if (find == null) {
            return false;
        } else {
            mem.remove(find);
        }
        return true;
    }


    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
