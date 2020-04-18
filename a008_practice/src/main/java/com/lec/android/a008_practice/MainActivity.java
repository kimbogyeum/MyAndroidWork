package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    InfobookAdapter adapter; //Adapter 객체
    RecyclerView rv;
    private final int REQUEST_CODE_CALC = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv=findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        adapter=new InfobookAdapter();

        rv.setAdapter(adapter);//RecyclerView에 Adapter 장착!
        Button btnadd=findViewById(R.id.btnadd);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(et1.getText().toString());
                int b = Integer.parseInt(et2.getText().toString());

                Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
                intent.putExtra("num1", a);
                intent.putExtra("num2", b);

                //값을 돌려받기 위한 화면전환
                startActivityForResult(intent, REQUEST_CODE_CALC);
            }
        });







    }

    private void initAdapter(InfobookAdapter adapter) {
    }
}
