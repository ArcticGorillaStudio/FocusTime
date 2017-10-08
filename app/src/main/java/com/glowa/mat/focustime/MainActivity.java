package com.glowa.mat.focustime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    TextView timeCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        timeCounter = (TextView) findViewById(R.id.txtTime);

        timeCounter.setText("00:00:000");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartCount(3);
            }
        });
    }

    public void StartCount(int minutes){
        int milliseconds = (minutes * 60) * 1000;

        new CountDownTimer(milliseconds, 100) {

            public void onTick(long millisUntilFinished){

                long s = millisUntilFinished / 1000;
                long m = s * 60;
                timeCounter.setText(String.format("%d:%02d:%02d", m,s,millisUntilFinished));
            }

            public void onFinish() {
                timeCounter.setText("done!");
            }
        }.start();

    }

}
