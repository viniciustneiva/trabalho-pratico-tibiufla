package br.ufla.gac106.s2022_1.Util;

import java.util.Random;

public class RandomNumber {
    
    public static Random random = new Random();

    public static int generateNumber(int range){
        return random.nextInt(range);
    }
}
