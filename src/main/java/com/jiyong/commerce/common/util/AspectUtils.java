package com.jiyong.commerce.common.util;


import java.util.concurrent.ThreadLocalRandom;

public class AspectUtils {

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
