package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 1;
        short b = 1100;
        int c = 15000;
        long d = 3000000000L;
        char e = 'E';
        float f = 3.14F;
        double g = 7.62;
        boolean h = true;
        LOG.debug("Variable output example : a = {}, b = {}, c = {}, d = {}" , a, b, c, d);
        LOG.debug("Variable output example : e = {}, f = {}, g = {}, h = {}" , e, f, g, h);
    }
}
