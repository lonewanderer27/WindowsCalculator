package com.jay_puzon.guicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // state
    double prevResult;
    String resultStr = "";
    double result;
    String operation = "";

    // UI
    TextView Result;
    TextView Equation;

    void doCE() {

    }

    void doC() {

    }

    void doBackspace() {

    }

    void doOper(String oper) {

    }

    void insertNum(int num) {
        try {
            // update state
            resultStr += String.valueOf(num);
            result = Double.parseDouble(resultStr);

            // update UI
            Result.setText(resultStr);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    void insertDot() {
        try {
            // update state
            resultStr += ".";
            result = Double.parseDouble(resultStr);

            // update UI
            Result.setText(resultStr);
        } catch (Exception e) {
            Log.e("ERROR: ", e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign display UIs
        Result = findViewById(R.id.result);
        Equation = findViewById(R.id.equation);

        Button DotButton = findViewById(R.id.dot);
        DotButton.setOnClickListener(view -> insertDot());

        Button[] NumButtons = new Button[] {
                findViewById(R.id.zero),
                findViewById(R.id.one),
                findViewById(R.id.two),
                findViewById(R.id.three),
                findViewById(R.id.four),
                findViewById(R.id.five),
                findViewById(R.id.six),
                findViewById(R.id.seven),
                findViewById(R.id.eight),
                findViewById(R.id.nine),
        };
        for (int i = 0; i < NumButtons.length; i++) {
            int finalI = i;
            NumButtons[finalI].setOnClickListener(view -> insertNum(finalI));
        }


        Button[] OperButtons = new Button[] {
                // 5 Main Operations
                findViewById(R.id.addition),
                findViewById(R.id.subtract),
                findViewById(R.id.multiply),
                findViewById(R.id.divide),
                findViewById(R.id.modulo),

                // 3 Extra Operations
                findViewById(R.id.sqrt),
                findViewById(R.id.raise),
                findViewById(R.id.half),

                // Evaluate
                findViewById(R.id.equal)
        };
    }
}