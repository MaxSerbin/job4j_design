package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private int count = 0;
    private final SimpleArray<T> set = new SimpleArray<>(count);


    @Override
    public boolean add(T value) {
        for (var i : set) {
            if (Objects.equals(value,i)) {
                return false;
            }
        }
        set.add(value);
        count++;
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (var i : set) {
            if (Objects.equals(value,i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
