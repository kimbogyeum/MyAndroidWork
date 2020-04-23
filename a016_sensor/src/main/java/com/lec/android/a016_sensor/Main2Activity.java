package com.lec.android.a016_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventCallback;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {

    private TextView tv;
    private SensorManager sm;

    //TYPE_ORIENTATION <-- 현재 deprecated 된
    Sensor accelerometer;
    Sensor magnetometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv=findViewById(R.id.textView1);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        //Sensor s=sm.getDefaultSensor(Sensor.TYPE_ORIENTATION); 옛날에 쓰던 방식

        //센서 얻어오기
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);




    }//end onCreate()

    //자원 가져오기
    //화면 동작하기 직전에 센서 자원획득
    @Override
    protected void onResume() {
        super.onResume();
        //센서값이 변경됬을때마다 콜백받기 위한 리스너 등록. SensorEventListener 객체
        sm.registerListener((SensorEventListener) this,
                accelerometer,
                SensorManager.SENSOR_DELAY_UI //지연시간, 2ms
        );


        sm.registerListener((SensorEventListener) this,
                magnetometer,
                SensorManager.SENSOR_DELAY_UI
        );



    }


    //화면 빠져나가기 전에 센서 자원 반납!
    @Override
    protected void onPause() {
        super.onPause();
        //센서에 등록된 리스너 해제
        sm.unregisterListener(this);
    }



    float[] mGravity;
    float[] mGeomagnetic;


    //센서값이 변경될때마다 호출되는 메소드
    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            mGravity=event.values; //센서값들은 float[]로 넘어옴

        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic=event.values;

        if(mGravity!=null && mGeomagnetic!=null){
            float[] R=new float[9];
            float[] I=new float[9];

            boolean success= SensorManager.getRotationMatrix(R,I,mGravity,mGeomagnetic);

            if(success){
                float [] orientation=new float[3];
                SensorManager.getOrientation(R,orientation);

                float azimuth=orientation[0]; // z축 회전방향(-3 ~ +3)
                float pitch=orientation[1]; // x축 회전방향 (1.5 ~ -1.5)
                float roll=orientation[2]; // y축 회전방향


                String str=String.format("%10s:%10s:%10s\n%10.2f:%10.2f:%10.2f","방위각","피치","롤",azimuth,pitch,roll); //전체적으론 10자리, 소수점 2자리

                tv.setText(str);
                Log.d("myapp",str);

            }

        }


    }

    //센서의 정확도가 변경되었을때 호출되는 콜백
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        tv.setText("onAccuracyChanged");
    }



}//end Activity
