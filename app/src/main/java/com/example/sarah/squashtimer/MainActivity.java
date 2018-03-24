package com.example.sarah.squashtimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.sarah.squashtimer.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.sarah.squashtimer.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.sarah.squashtimer.MESSAGE3";
    public static final String EXTRA_MESSAGE4 = "com.example.sarah.squashtimer.MESSAGE4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDrill(View view) {
        Intent intent = new Intent(this, Drill.class);
        EditText editText = (EditText) findViewById(R.id.noGhosts);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        EditText editText2 = (EditText) findViewById(R.id.ghostSpacing);
        String message2 = editText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2, message2);
        EditText editText3 = (EditText) findViewById(R.id.restTime);
        String message3 = editText3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE3, message3);
        EditText editText4 = (EditText) findViewById(R.id.noRepeats);
        String message4 = editText4.getText().toString();
        intent.putExtra(EXTRA_MESSAGE4, message4);
        startActivity(intent);
    }
}
