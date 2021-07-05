package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;


    public EvenIterator(int[] data) {
          this.data = data;
          point = -1;
          fixNext();
    }

    @Override
    public boolean hasNext() {
            return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer x = data[point];
        fixNext();
        return  x;
    }

    private void fixNext() {
        point += 1;
        while (hasNext() && data[point] % 2 != 0) {
            point += 1;
        }
    }


}
