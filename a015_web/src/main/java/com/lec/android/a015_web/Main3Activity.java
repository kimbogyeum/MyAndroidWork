package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * XML , JSON 파싱 연습
 *
 * ■서울시 지하철 역사 정보
      http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

      샘플url

      XML 버젼
      http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

      JSON 버젼
      http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울



 *      statnNm(STring), subwayId(int), subwayNm(String)
 *
 * //Java 32강 11번쨰 강의 참고
 */

public class Main3Activity extends AppCompatActivity {

    EditText editText;
    Button btnXML,btnJSON,btnParse;
    TextView tvResult;
    String state;
    String url_address;




    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        editText=findViewById(R.id.editText);
        btnJSON=findViewById(R.id.btnJSON);
        btnParse=findViewById(R.id.btnParse);
        btnXML=findViewById(R.id.btnXML);
        tvResult=findViewById(R.id.tvResult);

        url_address="";


        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String state=editText.getText().toString();
                url_address=buildUrlAdressx(state);
                //HTTP request 는 별도의 Thread 로 진행해야 한다.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(url_address); //Thread 안에 만들어야 한다.
                    }
                }).start();

            }
        });

        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state=editText.getText().toString();
                url_address=buildUrlAdressj(state);
                //HTTP request 는 별도의 Thread 로 진행해야 한다.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(url_address); //Thread 안에 만들어야 한다.
                    }
                }).start();
            }
        });





    }//end onCreate()

//    api_key=getResources().getString(R.string.api_key); → resource에 api 파일 만들어서 바뀔때 영향 안받게 만들 수 도 있어.

    public String buildUrlAdressx(String state) {
        String url_adress = String.format("http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/%s",
                state);
        return url_adress;
    }

    public String buildUrlAdressj(String state) {
        String url_adress = String.format("http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/%s",
                state);
        return url_adress;
    }

    public void request(String url_address){
        {
            final StringBuilder sb = new StringBuilder();

            BufferedReader reader = null;
            HttpURLConnection conn = null;

            try {
                URL url = new URL(url_address);
                conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    //timeout 시간 설정, 경과하면 SocketTimeoutException 발생
                    conn.setConnectTimeout(5000); //connect가 수립되는 시간 5초!
                    conn.setUseCaches(false); //캐시 사용 안함.
                    conn.setRequestMethod("GET"); //GET 방식 request

                    conn.setDoInput(true); //URLConnection을 입력으로 사용. (true), (false) -> 출력용

                    int responseCode = conn.getResponseCode(); //response code 값. 성공하면 200
                    if (responseCode == HttpURLConnection.HTTP_OK) { //200 HTTP_OK
                        reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); //connection으로부터 inputStream뽑고 그걸 inputstreamReader기에 장착->버퍼에 장착!
                        String line = null;
                        while (true) {
                            line = reader.readLine();
                            if (line == null) break;
                            sb.append(line + "\n");
                        }
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (conn != null) conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvResult.setText("응답" + sb.toString());
                }
            });
        }

    }// end request



}//end Activity
