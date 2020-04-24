package com.lec.android.a017_location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**  지오코딩(GeoCoding) : 주소,지명 => 위도(latitude),경도(longitude) 좌표로 변환하는 구글 서비스
 *    위치정보를 얻기위한 권한을 획득 필요, AndroidManifest.xml
 *      ACCESS_FINE_LOCATION : 현재 나의 위치를 얻기 위해서 필요함
 *      INTERNET : 구글서버에 접근하기위해서 필요함
 */

public class Main3Activity extends AppCompatActivity {
    TextView tvResult;
    EditText etLatitude, etLongitude;
    EditText etAddress;
    Button btnGeoCoder1, btnGeoCoder2;
    Button btnMap1, btnMap2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult =  findViewById(R.id.tvResult); // 결과창
        btnGeoCoder1 = findViewById(R.id.btnGeoCoder1);
        btnGeoCoder2 = findViewById(R.id.btnGeoCoder2);
        btnMap1 = findViewById(R.id.btnMap1);
        btnMap2 = findViewById(R.id.btnMap2);

        //지오코딩
        final Geocoder geocoder;
        geocoder=new Geocoder(this);

        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        etAddress = findViewById(R.id.etAddress);

        //#1 위도, 경도로 찾기
        btnGeoCoder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //위도, 경도 입력 후 주소정보 변환
                Double lat=Double.parseDouble(etLatitude.getText().toString());
                Double lng=Double.parseDouble(etLongitude.getText().toString());

                List<Address> list=null; //주소 정보를 담을 리스트


                try {
                   list= geocoder.getFromLocation(
                            lat, //위도
                            lng, //경도
                            10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력오류-서버에서 주소 반환시 에러발생");
                }

                if(list!=null){
                    if(list.size()==0){
                        tvResult.setText("해당되는 주소 정보가 없습니다.");
                    }else{
                        StringBuffer result=new StringBuffer(list.size()+"개의 결과\n");

                        for(Address addr:list){
                            result.append("-------------------\n");
                            result.append(addr.toString()+"\n");
                        }

                        tvResult.setText(result);
                    }
                }//end if


            }//end onclick
        });


        //#2 지명 이름으로 찾기
        btnGeoCoder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //지명,주소
                String str=etAddress.getText().toString();

                List<Address> list=null; //주소 정보를 담을 리스트


                try {
                    list= geocoder.getFromLocationName(  //위도, 경도와 다르게 뒤에 Name을 붙여준다.
                            str, //지명이름
                            10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력오류-서버에서 주소 반환시 에러발생");
                }

                if(list!=null){
                    if(list.size()==0){
                        tvResult.setText("해당되는 주소 정보가 없습니다.");
                    }else{
                        StringBuffer result=new StringBuffer(list.size()+"개의 결과\n");

                        for(Address addr:list){
                            result.append("-------------------\n");
                            result.append("Country: "+addr.getCountryName()
                                        +"Feature: "+addr.getFeatureName()
                                        +"Locality: "+addr.getLocality()
                                        +"Lat: "+addr.getLatitude()
                                        +"Lng: "+addr.getLongitude());
                        }

                        tvResult.setText(result);
                    }
                }//end if


            }//end onclick
        });


        btnMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //위도, 경도 입력 후 지도 버튼 클릭-> 지도 앱 화면ㅇ로 묵시적 인텐트 날리기
                Double lat=Double.parseDouble(etLatitude.getText().toString());
                Double lng=Double.parseDouble(etLongitude.getText().toString());

                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+lat+","+lng));
                startActivity(intent);
            }
        });

        btnMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //지명,주소
                String str=etAddress.getText().toString();

                List<Address> list=null; //주소 정보를 담을 리스트


                try {
                    list= geocoder.getFromLocationName(  //위도, 경도와 다르게 뒤에 Name을 붙여준다.
                            str, //지명이름
                            10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("myapp","입출력오류-서버에서 주소 반환시 에러발생");
                }

                if(list!=null){
                    if(list.size()==0){
                        tvResult.setText("해당되는 주소 정보가 없습니다.");
                    }else {
                        //해당되는 주소의 위치 좌표로 묵시적 인텐트 날리기(지도앱)
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();
                        double lng = addr.getLatitude();

                        String uri=String.format("geo:%f,%f",lat,lng);
                        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
                        startActivity(intent);
                    }

                }//end if


            }//end onclick
        });

    }//end onCreate()
}//end Activity
