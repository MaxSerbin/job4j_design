package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleArray<T> implements Iterable<T> {
    private final T[] array;
    private int countAddRemove;

    public SimpleArray(T[] models) {
        array = models;
    }

    public T[] getArray() {
        return array;
    }

    public void add(T model) {
            this.array[countAddRemove++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, countAddRemove);
        array[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, countAddRemove);
            return this.array[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, countAddRemove);
        System.arraycopy(array, index + 1, array, index, countAddRemove - index - 1);
        array[countAddRemove - 1] = null;
        countAddRemove--;
    }

    @Override
    public Iterator<T> iterator() {
        return new  Iterator<>() {
            private int countIt;

            @Override
            public boolean hasNext() {
                return countIt < countAddRemove;
            }

            @Override
            public T next() {
                if (countIt >= countAddRemove) {
                    throw new NoSuchElementException();
                }
                return get(countIt++);
            }
        };
    }
}
