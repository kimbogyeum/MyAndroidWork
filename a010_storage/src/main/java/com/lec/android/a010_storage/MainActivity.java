package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 안드로이드 에서 자료를 저장하는 4가지 수단
 *      1. 내부파일 사용 (Internal Storage) : '앱 데이터' 저장 영역
 *      2. 외부메모리 사용 (External Storage) : 사진, 동영상등 '사용자 영역'
 *      3. SQLite  (내장 DataBase)
 *      4. SharedPreference
 *
 *      ** 외부에 (서버, 네트워크, 외부 DB) 사용이 아닌 내부 저장수단
 *
 *      https://developer.android.com/training/data-storage
 *
 *  내부 파일 사용 (Internal Storage, App-specific storage)
 *      - 앱 데이터가 저장되는 영역
 *      - 별도의 permission 없이 사용 가능
 *      - 자신의 앱에서만 사용 가능, 다른 앱에서 접근 못함
 *      - 앱 제거시, Internal Storage 영역의 모든 데이터도 제거됨.
 *      - openFileOutput() 를 사용하여 저장  (  FileOutputStream 객체 리턴함 )
 */

//Device File Explorer에서 생성된 파일 확인가능
// Pixel2 폰의 경우 /data/data/com.lec.android.a010_storate/files/myfile.text
public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAppend, btnRead;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        btnAppend = findViewById(R.id.btnAppend);
        btnRead = findViewById(R.id.btnRead);
        tvResult = findViewById(R.id.tvResult);

        //추가하기 버튼 클릭하면 파일에 추가로 저장하기
        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data= et.getText().toString();

                //openFileOutput을 사용하여 OutputStream 객체 뽑아내기
                /*
                참고로 읽어오는 방식에는 Write,Append,Read 세가지가 있다.
                여기서는 Append mode로 파일을 끌어오는것이다.
                */

                try {
                    FileOutputStream os=openFileOutput("myfile.txt",MODE_APPEND); //openFileOutput은 안드로이드 제공, 이걸통해 file Stream을 뽑아낼 수 있다.
                    PrintWriter out=new PrintWriter(os); //writer,read으로 끝난다면 문자단위로 읽어드림.
                    out.println(data);
                    out.close();
                    tvResult.setText("파일저장완료");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //파일의 내용을 읽어서 보여주기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream is=openFileInput("myfile.txt");
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));

                    StringBuffer data=new StringBuffer();
                    String str=reader.readLine();//파일에서 한줄을 읽어오기
                    while (str!=null){
                        data.append(str+"\n");
                        str=reader.readLine();
                    }//end while

                    tvResult.setText(data);
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }//end onCreate()
}//end Activity