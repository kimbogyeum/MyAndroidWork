package com.lec.android.a008_practice;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //받고
        Intent intent=getIntent();
        int num1=intent.getIntExtra("num1",0);
        int num2=intent.getIntExtra("num2",0);

        //돌려주기
        intent.putExtra("plus",num1+num2);
        intent.putExtra("minus",num1-num2);

        //호출한 화면에 값 되돌려주기
        setResult(RESULT_OK,intent);

        finish(); //onDestroy()와 동일
    }// end onCreate()

}// end Activity
