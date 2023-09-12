package com.jay_puzon.guicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import org.nfunk.jep.JEP;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // initialize parser
    JEP jep = new JEP();

    // string operators
    String[] OperStrings = {
            "+",
            "-",
            "*",
            "/",
            "%",
            "**2",
            "/2",
    };  


    // state
    String resultStr = "";
    double result;
    ArrayList<String> equation = new ArrayList<String>();

    // UI
    TextView Result;
    TextView Equation;
    String EquationStr = "";


//    void doCE() {
//        // update state
//        resultStr = "";
//        result = 0;
//
//        // update UI
//        Result.setText(0);
//
//        doEval();
//    }
//
//    void doC() {
//        // do clear;
//        doCE();
//
//        // update state
//        equation.clear();
//
//        // update UI
//        Equation.setText("");
//        doEval();
//    }
//
//    void doEval() {
//        try {
//            jep.parseExpression(
//                    equation.toString()
//                            .replace("[", "")
//                            .replace("]", ""));
//            result = jep.getValue();
//            resultStr = String.valueOf(result);
//        } catch (Exception e) {
//            result = 0;
//            resultStr = "";
//        }
//    }
//
//    void doBackspace() {
//        try {
//            // update state
//             equation.remove(equation.size() -1 );
//
//             // evaluate current equation
//             doEval();
//        } catch (Exception e) {
//            Log.e("ERROR: ", e.getMessage());
//        }
//    }
//
//    void doOper(String operator) {
//        try {
//            boolean operFound = false;
//            // check if the last character is an operator
//            for (String c: OperStrings) {
//                if (c.equals(operator)) {
//                    operFound = true;
//                    break;
//                }
//            }
//
//            // if it is an operator from OperStrings, then replace it
//            if (operFound) {
//                equation.set(-1, operator);
//            } else {
//                // otherwise, add the operator at the end
//                equation.add(operator);
//            }
//
//            // update UI
//            if (equation.size() != 0) {
//                Equation.setText(equation.toString().replace("[", "").replace("]", ""));
//            }
//
//            // evaluate current equation
//            doEval();
//        } catch (Exception e) {
//            Log.e("ERROR", e.getMessage());
//        }
//    }
//
//    void insertNum(int num) {
//        try {
//            // update state
//            resultStr += String.valueOf(num);
//            result = Double.parseDouble(resultStr);
//
//            // update UI
//            Result.setText(resultStr);
//
//            // evaluate current operation
//            doEval();
//        } catch (Exception e) {
//            Log.e("ERROR", e.getMessage());
//
//            doEval();
//        }
//    }
//
//    void insertDot() {
//        try {
//            // update state
//            resultStr += ".";
//            result = Double.parseDouble(resultStr);
//
//            // update UI
//            Result.setText(resultStr);
//
//            doEval();
//        } catch (Exception e) {
//            Log.e("ERROR: ", e.getMessage());
//        }
//    }

    void insertDot() {
        // only add dot if the last character is not a dot
        if (equation.get(equation.size()-1) != ".") {
            equation.add(".");
        }

        doCompute();
    }

    void doBackspace() {
        // only erase the last character if the equation's size is not zero
        if (equation.size() > 0) {
            Log.i("INFO", "BACKSPACE");
            Log.i("BEFORE REMOVE", equation.toString());
            // equation size is 1, then remove all tokens
            if (equation.size() == 1) {
                equation.clear();
            } else {
                // otherwise remove the last item
                Integer lastItemIndex = equation.size() - 1;
                equation.remove(lastItemIndex+"");
            }

            Log.i("AFTER REMOVE", equation.toString());
        } else {
            // otherwise replace the equation with zero
            equation.removeAll(equation);
            Log.e("ERROR", "Equation is empty");
        }

        doCompute();
    }


    void doEval() {

    }


    void insertNum(Integer number) {
        equation.add(number+"");
        doCompute();
    }

    void addOper(String newOper) {
        // get the last character in equation array
        Integer lastItemIndex = equation.size() - 1;
        String lastItem = equation.get(lastItemIndex);

        // test if the last character is an operator
        Boolean operatorFound = false;

        // loop over the array of operator strings
        for (String oper : OperStrings) {
            if (oper.equals(lastItem)) {
                // set it to true if an operator was found
                operatorFound = true;
                break;
            }
        }

        if (operatorFound) {
            // if operator is found, then replace it in the equation array
            equation.set(lastItemIndex, newOper);
        } else {
            // otherwise, just add the operator
            equation.add(newOper);
        }

        doCompute();
    }

    void parse() {
        // get the string representation of the equation
        String equationStr = "";

        // loop over each number / operator
        // then appending it to equationStr
        for (String token: equation) {
            // if the token is a dot, then just append it
            if (token.equals(".")) {
                equationStr += token;
                continue;
            }

            // test if we can convert the token to a double
            try {
                Double.parseDouble(token);
                equationStr += token;
            } catch (Exception e) {
                // if not, then it is an operator
                // so we add a space before and after the operator
                equationStr += " " + token + " ";
            }
        }

        EquationStr = equationStr;
        Equation.setText(equationStr);
    }

    void doCompute() {
        // parse the equation arraylist
        parse();

        try {
            Log.i("Equation:", EquationStr);

            // evaluate the equation string
            jep.parseExpression(EquationStr);
            result = jep.getValue();
            resultStr = String.valueOf(result);

            // if the computed value is 0.0, then just erase the resultStr
            if (result == 0.0) {
                resultStr = "";
            }

            // remove trailing .0 if it's unnecessary
            if (resultStr.endsWith(".0")) {
                resultStr = resultStr.substring(0, resultStr.length() -2);
            }
        } catch (Exception e) {
            result = 0;
            resultStr = "";
            Result.setText("");

            Log.e("ERROR", e.getMessage());
        }

        // update UI
        Log.i("RESULT", result+"");
        Result.setText(resultStr);
    }

    void doC() {
        // update state
        result = 0;
        resultStr = "";
        Result.setText("");

        equation.removeAll(equation);
        Equation.setText("");
        EquationStr = "";

        Result.setText("0");
    }

    void doCE() {
        // update state
        result = 0;
        resultStr = "";
        Result.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add constants and standard functions to JEP
        jep.addStandardConstants();
        jep.addStandardFunctions();

        // assign display UIs
        Result = findViewById(R.id.result);
        Equation = findViewById(R.id.equation);

        // assign control buttons
        Button DotButton = findViewById(R.id.dot);
        Button BackspaceButton = findViewById(R.id.backspace);
        Button CEButton = findViewById(R.id.ce);
        Button CButton = findViewById(R.id.c);
        DotButton.setOnClickListener(view -> insertDot());
        BackspaceButton.setOnClickListener(view -> doBackspace());
//        CEButton.setOnClickListener(view -> doCE());
        CButton.setOnClickListener(view -> doC());

        // assign number buttons
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

        // assign operation buttons
        Button[] OperButtons = new Button[] {
                // 5 Main Operations
                findViewById(R.id.addition),
                findViewById(R.id.subtract),
                findViewById(R.id.multiply),
                findViewById(R.id.divide),
                findViewById(R.id.modulo),

                // 3 Extra Operations
//                findViewById(R.id.raise),
//                findViewById(R.id.half),
//                findViewById(R.id.sqrt)
        };
        for (int i = 0; i < OperButtons.length; i++) {
            int finalI = i;
            OperButtons[finalI].setOnClickListener(view -> addOper(OperStrings[finalI]+""));
        }

        // assign evaluate button;
        Button EvalButton = findViewById(R.id.equal);
        EvalButton.setOnClickListener(view -> doEval());
    }
}