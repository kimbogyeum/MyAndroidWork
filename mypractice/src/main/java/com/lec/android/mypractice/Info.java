package com.lec.android.mypractice;

public class Info {
    public static String[] name1={


    };

    public static int[] age1={

    };

    public static final String[] address1={

    };

    private static int idx = 0;

    public static int next() {
        idx = idx % name1.length;
        return idx++;   // idx 값 리턴하고 1증가
    }
}// end