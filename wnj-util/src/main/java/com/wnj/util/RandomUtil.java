package com.wnj.util;

import java.util.Random;

public class RandomUtil {
    public static int random(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}
