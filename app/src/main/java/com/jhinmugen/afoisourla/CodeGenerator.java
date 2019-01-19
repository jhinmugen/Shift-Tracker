package com.jhinmugen.afoisourla;


import java.util.Calendar;
import java.util.Random;

public class CodeGenerator {
    private int seed;
    private int code;
    public static final int UPPER_LIMIT = 9999;
    public static final int LOWER_LIMIT = 1000;

    public CodeGenerator(){
        seed = Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    public int randomGenerator(){
        Random random = new Random(seed);
        code = random.nextInt(UPPER_LIMIT-LOWER_LIMIT) + LOWER_LIMIT;

        return code;
    }

}
