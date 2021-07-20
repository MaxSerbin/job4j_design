package ru.job4j.collection;

import java.util.*;


public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int count = 0;
    private int modCount = 0;

    public SimpleArray(int count) {
        this.container = new Object[count];
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) container[index];
    }

    public void add(T model) {
        if(count >= container.length) {
            container = Arrays.copyOf(container, container.length + 10);
        }
            container[count++] = model;
            modCount++;
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
                return countIt < count;
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
