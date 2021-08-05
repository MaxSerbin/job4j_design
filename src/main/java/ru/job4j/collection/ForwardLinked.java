package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }


    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> delNode = head;
            head = head.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (head != null) {
            node.next = head;
        }
            head = node;
            size++;
    }

    public boolean revert() {
        if(head == null || head.next == null){
            return false;
        }
        Node<T> prev = null;
        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            if(next == null){
                head = curr;
            }
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return true;
    }


        @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
