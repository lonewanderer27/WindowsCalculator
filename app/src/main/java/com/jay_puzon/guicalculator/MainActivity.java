package com.jay_puzon.guicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button[] NumButtons = new Button[] {
                findViewById(R.id.one),
                findViewById(R.id.two),
                findViewById(R.id.three),
                findViewById(R.id.four),
                findViewById(R.id.five),
                findViewById(R.id.six),
                findViewById(R.id.seven),
                findViewById(R.id.eight),
                findViewById(R.id.nine),
                findViewById(R.id.zero),
                findViewById(R.id.dot)
        };

        Button[] OperButtons = new Button[] {
                findViewById(R.id.modulo),
                findViewById(R.id.sqrt),
                findViewById(R.id.raise),
                findViewById(R.id.half),
                findViewById(R.id.divide),
                findViewById(R.id.multiply),
                findViewById(R.id.addition),
                findViewById(R.id.subtract),
                findViewById(R.id.addition),
                findViewById(R.id.equal)
        };


    }
}