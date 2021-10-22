package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("-name"), is("Max Serbin"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("-name"), is("Max Serbin"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongPair() {
        String path = "./data/wrong_pair.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongPair2() {
        String path = "./data/wrong_pair2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongPair3() {
        String path = "./data/wrong_pair3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenWrongPair4() {
        String path = "./data/wrong_pair4.properties";
        Config config = new Config(path);
        config.load();
    }




}