package ru.job4j.collection;

import java.util.*;


public class SimpleArray<T> implements Iterable<T> {
    Object[] container;
    private int count = 0;
    private int modCount = 0;

    public SimpleArray( Object[] container) {
        this.container = container;
    }

    public T[] getArray() {
        return (T[]) container;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) container[index];
    }

    public void add(T model) {
        if(count >= container.length) {
            container = Arrays.copyOf(container, container.length + 1);
            container[count++] = model;
            modCount++;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int countIt = 0;
            private final int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return countIt < container.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[countIt++];
            }
        };
    }
}
