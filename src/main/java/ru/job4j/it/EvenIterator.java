package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;


    public EvenIterator(int[] data) {
        int[] tmp = new int[data.length];
        int counter = 0;
        for (int num : data) {
            if(num % 2 == 0){
                tmp[counter++] = num;
            }
        }
          this.data = Arrays.copyOf(tmp, counter);

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
        return data[point++];
    }
}
