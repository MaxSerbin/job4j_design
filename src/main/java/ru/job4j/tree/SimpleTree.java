package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> x = findByPredicate((f) -> f.children.size() > 2);
        return x.isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((f) -> f.value.equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
            return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> prnt = findBy(parent);
        Optional<Node<E>> chldrn = findBy(child);
        if (prnt.isPresent() && chldrn.isEmpty()) {
            Node<E> node = new Node<>(child);
            prnt.get().children.add(node);
            rsl = true;
        }
        return rsl;
    }
}
