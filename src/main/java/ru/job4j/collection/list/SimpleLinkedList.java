package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private transient int size = 0;
    private transient int modCount = 0;
    private transient Node<E> first;
    private transient Node<E> last;


    @Override
    public void add(E value) {
        Node<E> lst = last;
        Node<E> node = new Node<>(lst, value, null);
        last = node;
        if (lst == null) {
            first = node;
        } else {
            lst.next = node;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return first.el;
        } else if (index + 1 == size) {
            return last.el;
        }
        Node<E> rsl;
        if (index < size / 2) {
            rsl = last;
            for (int i = size - 1; i > index; i--) {
                rsl = rsl.prev;
            }
        } else {
            rsl = first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        }
        return rsl.el;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> counterIt = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return counterIt != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = counterIt.el;
                counterIt = counterIt.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E el;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E el, Node<E> next) {
            this.prev = prev;
            this.el = el;
            this.next = next;

        }
    }
}
