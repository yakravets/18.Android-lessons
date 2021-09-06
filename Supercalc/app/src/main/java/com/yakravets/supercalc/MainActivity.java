package com.yakravets.supercalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private String currentSymbol;
    private String operation;
    private boolean hasOperation = false;
    private boolean hasDigitsInScreen = false;
    private boolean hasDotInNumber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        result.setText("0");
    }

    private void addDigitToResult(String symbol){

        if(hasDotInNumber && symbol == ".")
            return;

        if(result.getText().length() < 1)
            return;

        if(hasDigitsInScreen || (!hasDotInNumber && symbol == "."))
        {
            result.setText(result.getText() + symbol);
        }
        else
        {
            result.setText(symbol);
            hasDigitsInScreen = true;
        }
    }

    public void digitBtnOnClick1(View view){
        addDigitToResult("1");
    }

    public void digitBtnOnClick2(View view){
        addDigitToResult("2");
    }

    public void digitBtnOnClick3(View view){
        addDigitToResult("3");
    }

    public void digitBtnOnClick4(View view){
        addDigitToResult("4");
    }

    public void digitBtnOnClick5(View view){
        addDigitToResult("5");
    }

    public void digitBtnOnClick6(View view){
        addDigitToResult("6");
    }

    public void digitBtnOnClick7(View view){
        addDigitToResult("7");
    }

    public void digitBtnOnClick8(View view){
        addDigitToResult("8");
    }

    public void digitBtnOnClick9(View view){
        addDigitToResult("9");
    }

    public void digitBtnOnClick0(View view){
        addDigitToResult("0");
    }

    public void equalsBtnOnClick1(View view){

        if(!hasOperation)
            return;

        String[] res = result.getText().toString().split(Pattern.quote(operation));
        if(res.length < 2)
            return;

        if(operation == "/" && parseInt(res[1]) == 0) {
            Toast.makeText(this, "Division by zero", Toast.LENGTH_SHORT).show();
            return;
        }

        addDigitToResult("=");
        switch (operation)
        {
            case "+":
                double add = parseDouble(res[0]) + parseDouble(res[1]);
                addDigitToResult(Double.toString(add));
                break;
            case "-":
                double subs = parseDouble(res[0]) - parseDouble(res[1]);
                addDigitToResult(Double.toString(subs));
                break;
            case "*":
                double multy = parseDouble(res[0]) * parseDouble(res[1]);
                addDigitToResult(Double.toString(multy));
                break;
            case "/":
                double division = parseDouble(res[0]) / parseDouble(res[1]);
                addDigitToResult(Double.toString(division));
                break;
        }

        hasDigitsInScreen = false;
        hasDotInNumber = false;
        hasOperation = false;

    }

    public void dotBtnOnClick1(View view){
        addDigitToResult(".");
        hasDotInNumber = true;

    }

    public void plusBtnOnClick(View view){
        operation = "+";
        hasOperation = true;
        hasDotInNumber = false;

        addDigitToResult("+");
    }

    public void minusBtnOnClick(View view){
        operation = "-";
        hasOperation = true;
        hasDotInNumber = false;

        addDigitToResult("-");
    }

    public void multiplyBtnOnClick(View view){
        operation = "*";
        hasOperation = true;
        hasDotInNumber = false;

        addDigitToResult("*");
    }

    public void divisionBtnOnClick(View view){
        operation = "/";
        hasOperation = true;
        hasDotInNumber = false;

        addDigitToResult("/");
    }

    public void clearBtnOnClick(View view){
        result.setText("0");

        hasOperation = false;
        hasDigitsInScreen = false;
        hasDotInNumber = false;
    }
}