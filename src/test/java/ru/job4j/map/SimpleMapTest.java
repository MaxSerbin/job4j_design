package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {


    @Test
    public void whenPutAndGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(2, "BBB");
        assertThat(map.get(1), is("AAA"));
        assertThat(map.get(2), is("BBB"));

    }

    @Test
    public void whenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void whenPutAndRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        assertTrue(map.remove(1));
        assertNull(map.get(1));

    }

    @Test
    public void whenPutDuplicate() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "AAA"));
        assertFalse(map.put(1, "BBB"));
    }

    @Test
    public void whenPutThenIt() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        map.put(2, "BBB");
        map.put(3, "CCC");
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenItEmpty() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenItThenPut() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "AAA");
        Iterator<Integer> it = map.iterator();
        map.put(2, "BBB");
        it.next();
    }

    @Test
    public void whenExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "AAA"));
        assertTrue(map.put(2, "BBB"));
        assertTrue(map.put(3, "CCC"));
        assertTrue(map.put(4, "DDD"));
        assertTrue(map.put(5, "EEE"));
        assertTrue(map.put(6, "FFF"));
        assertTrue(map.put(7, "GGG"));
        assertTrue(map.put(8, "HHH"));
        assertTrue(map.put(9, "III"));
        assertTrue(map.put(10, "JJJ"));
        assertThat(map.get(1), is("AAA"));
        assertThat(map.get(2), is("BBB"));
        assertThat(map.get(3), is("CCC"));
        assertThat(map.get(4), is("DDD"));
        assertThat(map.get(5), is("EEE"));
        assertThat(map.get(6), is("FFF"));
        assertThat(map.get(7), is("GGG"));
        assertThat(map.get(8), is("HHH"));
        assertThat(map.get(9), is("III"));
        assertThat(map.get(10), is("JJJ"));
    }

}