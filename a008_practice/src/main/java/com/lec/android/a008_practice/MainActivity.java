package com.lec.android.a008_practice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    InfobookAdapter adapter; //Adapter 객체
    RecyclerView rv;
    EditText name,age,address;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);

        rv=findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager
                =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);

        adapter=new InfobookAdapter();


        btnadd=findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(v);
                rv.setAdapter(adapter);//RecyclerView에 Adapter 장착!
            }
        });


    }//end onCreate()

    protected void insertData(View v) {

    String a = name.getText().toString();
    String b = age.getText().toString();
    String c= address.getText().toString();


    adapter.addItem(0,new Infobook(a,b,c));
    adapter.notifyDataSetChanged();

    }//end insertData()

}//end Activity
