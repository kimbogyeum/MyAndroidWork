package com.lec.android.a008_practice;

public class Info {
    public static String[] name1={

            "아이언맨", "캡틴아메리카"
    };

    public static String[] age1={
            "12","13"
    };

    public static final String[] address1={
            "서울시 강남구","서울시 금천구"

    };

    private static int idx = 0;

    public static int next() {
        idx = idx % name1.length;
        return idx++;   // idx 값 리턴하고 1증가
    }
}// end