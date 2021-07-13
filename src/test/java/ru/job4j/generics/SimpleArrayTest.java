package ru.job4j.generics;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void whenAdd() {
        SimpleArray<Integer> integers = new SimpleArray<>(new Integer[5]);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Integer[] expect = new Integer[]{1, 2, 3, null, null};
        assertThat(expect, is(integers.getArray()));
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> integers = new SimpleArray<>(new Integer[5]);
        integers.add(2);
        integers.add(3);
        integers.add(1);
        integers.set(0, 1);
        integers.set(1, 2);
        integers.set(2, 3);
        Integer[] expect = new Integer[]{1, 2, 3, null, null};
        assertThat(expect, is(integers.getArray()));
    }

    @Test
    public void whenGet() {
        SimpleArray<Integer> integers = new SimpleArray<>(new Integer[5]);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        assertThat(2, is(integers.get(1)));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> integers = new SimpleArray<>(new Integer[5]);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.remove(1);
        Integer[] expect = new Integer[]{1, 3, 4, 5, null};
        assertThat(integers.getArray(), is(expect));
    }

    @Test//(expected = NoSuchElementException.class)
    public void hasNext() {
        SimpleArray<Integer> integers = new SimpleArray<>(new Integer[5]);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        Iterator<Integer> iterator = integers.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(false));
        integers.iterator().next();
    }
}