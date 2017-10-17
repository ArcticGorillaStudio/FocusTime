package com.glowa.mat.focustime;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    ProgressBar circProgressBar;
    EditText time;
    Vibrator vibrator;
    boolean isCounting = false;
    public Long milliLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        circProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        time = (EditText) findViewById(R.id.textView2);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isCounting) {
                    int iloscMinut = Integer.parseInt(String.valueOf(time.getText()));
                    StartCount(iloscMinut);
                }else{

                }
            }
        });




    }

    public void StartCount(int minutes){
        int milliseconds = (minutes * 60) * 1000;

        btnStart.setText("pause");
        btnStart.setEnabled(false);
        time.setEnabled(false);
        isCounting = true;



        new CountDownTimer(milliseconds, 1000) {
            int counter = 0;

            public void onTick(long millisUntilFinished){

               // Log.i("info", String.valueOf(millisUntilFinished));
                milliLeft = millisUntilFinished;
                Long min = (millisUntilFinished/(1000*60));
                Long sec = ((millisUntilFinished/1000)-min*60);

                circProgressBar.setProgress(counter);

                time.setText(Long.toString(min) + ":" + Long.toString(sec));
               // time.setText(String.valueOf(millisUntilFinished));

                counter++;
            }

            public void onFinish() {
                if(vibrator.hasVibrator()){
                    int i=0;
                    while(i <= 2){
                        vibrator.vibrate(1000);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }

                }

                counter=0;
                time.setText("5");
                btnStart.setEnabled(true);
                time.setEnabled(true);
                btnStart.setText("start");
            }
        }.start();

    }

}
