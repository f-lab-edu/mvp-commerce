package com.jiyong.commerce.common.util;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max!!");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
