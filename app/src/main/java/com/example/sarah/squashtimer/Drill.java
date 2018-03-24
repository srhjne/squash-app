package com.example.sarah.squashtimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Drill extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private int nCounter = 0;
    private String importantInfo[] = {
            "Back Right",
            "Back Left",
            "Center left",
            "Front left",
            "Front right",
            "Center right"
    };
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill);

        Button hButton = (Button)findViewById(R.id.idButton);
        hButton.setOnClickListener(mButtonStartListener);
        Button hButtonStop = (Button)findViewById(R.id.idButtonStop);
        hButtonStop.setOnClickListener(mButtonStopListener);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "Ready?";
        final long noGhosts = Long.parseLong(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

//        for (int i = 0; i < 4; i++) {
//            try {
//                mHandler.postDelayed(SleepMessages, 1000);
//            } catch (Exception e) {
//
//            }
//        }
    }

    View.OnClickListener mButtonStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = getIntent();
            final long noGhosts = Long.parseLong(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
            final long ghostSpacing = Long.parseLong(intent.getStringExtra(MainActivity.EXTRA_MESSAGE2));
            final long restTime = Long.parseLong(intent.getStringExtra(MainActivity.EXTRA_MESSAGE3));
            final long noRepeat = Long.parseLong(intent.getStringExtra(MainActivity.EXTRA_MESSAGE4));

            final long timeInterval = 1000*ghostSpacing;
            long offSet = 500;
            final Random randomizer = new Random();
            final long cutOff = noGhosts*timeInterval;
            final long intervalTime = 1000*(ghostSpacing*noGhosts+restTime);
            final long totalTime = intervalTime*noRepeat;



            new CountDownTimer(totalTime, timeInterval) {
                TextView textView = findViewById(R.id.textView);
                public void onTick(long millisUntilFinished) {
//                    long nCounter = millisUntilFinished/timeInterval - 1;
//                    String message = importantInfo[(int) nCounter];
                    if ((totalTime - millisUntilFinished) % intervalTime < cutOff) {
                        String message = importantInfo[randomizer.nextInt(importantInfo.length)];
                        textView.setText(message);
                    } else {
                        textView.setText("REST");
                    }
                }

                public void onFinish() {
                    textView.setText("done!");
                }
            }.start();



//            try {
//                mHandler.removeCallbacks(SleepMessages);
//                //        Parameters
//                //        r  The Runnable that will be executed.
//                //        delayMillis  The delay (in milliseconds) until the Runnable will be executed.
//
////                mHandler.postDelayed(SleepMessages, 1000); // delay 1 second
//                SleepMessages.run();
//            } catch (Exception e) {
//                TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    };

    private Runnable SleepMessages = new Runnable() {
            public void run(){
            TextView textView = findViewById(R.id.textView);
            try {
                for (nCounter = 0; nCounter < importantInfo.length; nCounter++) {
                    textView.postInvalidate();
                    String message = importantInfo[nCounter];
                    textView.setText(nCounter + message);
                    textView.postInvalidate();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e){

            }
      }
    };


    View.OnClickListener mButtonStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            mHandler.removeCallbacks(SleepMessages);

        }
    };
}
