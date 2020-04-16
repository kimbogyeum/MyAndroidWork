package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.hardware.TriggerEvent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText op1, op2;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        tvResult = findViewById(R.id.tvResult);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oper1=op1.getText().toString();
                String oper2=op2.getText().toString();

                int a,b;
                if(oper1 !=null && !oper1.trim().equals("")){
                    a=Integer.parseInt(oper1);
                }else{
                    a=0;
                }

                if(oper2 !=null && !oper1.trim().equals("")){
                    b=Integer.parseInt(oper2);
                }else{
                    b=0;
                }

                tvResult.setText((a+b)+"");
            }

        });


        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oper1=op1.getText().toString();
                String oper2=op2.getText().toString();

                int a,b;
                if(oper1 !=null && !oper1.trim().equals("")){
                    a=Integer.parseInt(oper1);
                }else{
                    a=0;
                }

                if(oper2 !=null && !oper1.trim().equals("")){
                    b=Integer.parseInt(oper2);
                }else{
                    b=0;
                }

                tvResult.setText((a-b)+"");
            }

        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oper1=op1.getText().toString();
                String oper2=op2.getText().toString();

                int a,b;
                if(oper1 !=null && !oper1.trim().equals("")){
                    a=Integer.parseInt(oper1);
                }else{
                    a=0;
                }

                if(oper2 !=null && !oper1.trim().equals("")){
                    b=Integer.parseInt(oper2);
                }else{
                    b=0;
                }

                tvResult.setText((a*b)+"");
            }

        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oper1=op1.getText().toString();
                String oper2=op2.getText().toString();

                int a,b;
                if(oper1 !=null && !oper1.trim().equals("")){
                    a=Integer.parseInt(oper1);
                }else{
                    a=0;
                }

                if(oper2 !=null && !oper1.trim().equals("")){
                    b=Integer.parseInt(oper2);
                }else{
                    b=0;
                }

                tvResult.setText((a/b)+"");
            }

        });

    } // end onCreate()
} // end Activity