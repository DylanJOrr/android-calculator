package com.example.seglab1_androidcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Text Views
    TextView currNum, currCalc, prevCalc;

    // Temp strings
    String prevCalcValue, add, minus, multiply, divide, defaultNum, defaultPrev, defaultCurr, errorText, zeroText, oneText, twoText, threeText, fourText, fiveText, sixText, sevenText, eightText, nineText;

    // Booleans
    private boolean showPrevCalc, radMode;
    private Button btnSin, btnCos, btnTan, btnInverseSin, btnInverseCos, btnInverseTan, btnHyperSin, btnHyperCos, btnHyperTan, btnInverseHyperSin, btnInverseHyperCos, btnInverseHyperTan, btnSquareRoot, btnCubeRoot, btnLog, btnNaturalLog, btnInvert, btne, btnPi, btneExponent, btnSquared, btnCubed, btnXPowerY, btnAbsoluteValue, btn2PowerX, btnFactorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        showPrevCalc = false;
        radMode = false;

        currNum = findViewById(R.id.current_number);
        currCalc = findViewById(R.id.current_calculation);
        prevCalc = findViewById(R.id.previous_calculation);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(currNum, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        btnSquareRoot = findViewById(R.id.button_squareRoot);
        btnCubeRoot = findViewById(R.id.button_cubeRoot);
        btnSin = findViewById(R.id.button_sin);
        btnInverseSin = findViewById(R.id.button_inverseSin);
        btnCos = findViewById(R.id.button_cos);
        btnInverseCos = findViewById(R.id.button_inverseCos);
        btnTan = findViewById(R.id.button_tan);
        btnInverseTan = findViewById(R.id.button_inverseTan);
        btnNaturalLog = findViewById(R.id.button_naturalLog);
        btnHyperSin = findViewById(R.id.button_hyperSin);
        btnLog = findViewById(R.id.button_log);
        btnHyperCos = findViewById(R.id.button_hyperCos);
        btnInvert = findViewById(R.id.button_invert);
        btnHyperTan = findViewById(R.id.button_hyperTan);
        btne = findViewById(R.id.button_e);
        btneExponent = findViewById(R.id.button_eExponent);
        btnInverseHyperSin = findViewById(R.id.button_inverseHyperSin);
        btnSquared = findViewById(R.id.button_squared);
        btnInverseHyperCos = findViewById(R.id.button_inverseHyperCos);
        btnXPowerY = findViewById(R.id.button_XPowerY);
        btnInverseHyperTan = findViewById(R.id.button_inverseHyperTan);
        btnAbsoluteValue = findViewById(R.id.button_absoluteValue);
        btn2PowerX = findViewById(R.id.button_2PowerX);
        btnPi = findViewById(R.id.button_pi);
        btnCubed = findViewById(R.id.button_cubed);
        btnFactorial = findViewById(R.id.button_factorial);

        zeroText = getResources().getString(R.string.zeroText);
        oneText = getResources().getString(R.string.oneText);
        twoText = getResources().getString(R.string.twoText);
        threeText = getResources().getString(R.string.threeText);
        fourText = getResources().getString(R.string.fourText);
        fiveText = getResources().getString(R.string.fiveText);
        sixText = getResources().getString(R.string.sixText);
        sevenText = getResources().getString(R.string.sevenText);
        eightText = getResources().getString(R.string.eightText);
        nineText = getResources().getString(R.string.nineText);

        add = getResources().getString(R.string.additionText);
        minus = getResources().getString(R.string.subtractText);
        multiply = getResources().getString(R.string.multiplyText);
        divide = getResources().getString(R.string.divideText);

        errorText = getResources().getString(R.string.errorText);
        defaultNum = getResources().getString(R.string.currentNumberText);
        defaultCurr = getResources().getString(R.string.currentCalculationText);
        defaultPrev = getResources().getString(R.string.previousCalculationText);
    }

    private void addText(String text) {
        String str = currNum.getText().toString();
        if (str.equals(defaultNum) || str.equals(errorText)) {
            if (text.equals(".0")) {
                str += text;
                currNum.setText(str);
                return;
            }
            currNum.setText(text);
            return;
        }
        switch (text) {
            case "+":
            case "-":
            case "*":
            case "×":
            case "/":
            case "÷":
                switch (str.charAt(str.length() - 1)) {
                    case '+':
                    case '-':
                    case '*':
                    case '×':
                    case '/':
                    case '÷':
                        str = str.substring(0, str.length() - 1);
                        break;
                }
                break;
            case ".":
                char[] arr = str.toCharArray();
                for (int i = str.length()-1; i >= 0; i--) {
                    switch (str.charAt(str.length() - 1)) {
                        case '+':
                        case '-':
                        case '*':
                        case '×':
                        case '/':
                        case '÷':
                            break;
                        case '.':
                            return;
                    }
                }
        }
        str+=text;
        currNum.setText(str);
    }

    @Override
    public void onClick(View view) {
        autoEval();
    }

    public void btn_0(View view) {
        addText(zeroText);
        autoEval();
    }

    public void btn_1(View view) {
        addText(oneText);
        autoEval();
    }

    public void btn_2(View view) {
        addText(twoText);
        autoEval();
    }

    public void btn_3(View view) {
        addText(threeText);
        autoEval();
    }

    public void btn_4(View view) {
        addText(fourText);
        autoEval();
    }

    public void btn_5(View view) {
        addText(fiveText);
        autoEval();
    }

    public void btn_6(View view) {
        addText(sixText);
        autoEval();
    }

    public void btn_7(View view) {
        addText(sevenText);
        autoEval();
    }

    public void btn_8(View view) {
        addText(eightText);
        autoEval();
    }

    public void btn_9(View view) {
        addText(nineText);
        autoEval();
    }

    // Misc
    public void btn_dot(View view) {
        addText(".");
        autoEval();
    }
    public void btn_brackets(View view) {
        String str = currNum.getText().toString();
        int ascii = str.charAt(str.length()-1);
        if (ascii < 48 || ascii > 57)
            return;
        str = "("+str+")";
        currNum.setText(str);
        autoEval();
    }
    public void btn_percent(View view) {
        addText("÷100");
        try {
            currNum.setText(evaluate(normalize(currNum)));
        } catch (ScriptException e) {
            currNum.setText(getResources().getString(R.string.errorText));
        }
        currCalc.setVisibility(View.INVISIBLE);
        autoEval();
    }
    public void btn_sign(View view) {
        String str = currNum.getText().toString();
        if (str.equals(getResources().getString(R.string.currentNumberText)))
            return;
        if (str.charAt(0) == minus.charAt(0)) {
            str = str.substring(1);
        } else {
            str = minus + str;
        }
        currNum.setText(str);
        autoEval();
    }
    public void btn_backspace(View view) {
        String str = currNum.getText().toString();
        if(str.length() == 0)
            return;
        str = str.substring(0, str.length() - 1);
        currNum.setText(str);
        autoEval();
    }

    // Operators
    public void btn_add(View view) {
        addText(add);
    }

    public void btn_subtract(View view) {
        addText(minus);
    }

    public void btn_multiply(View view) {
        addText(multiply);
    }

    public void btn_divide(View view) {
        addText(divide);
    }

    public void btn_equals(View view) {
        String result;
        try {
            result = evaluate(normalize(currNum));
            currNum.setText(result);
            if (showPrevCalc) {
                prevCalc.setText(prevCalcValue);
                prevCalc.setVisibility(View.VISIBLE);
            }
            showPrevCalc = true;
            prevCalcValue = result;
            autoEval();
        } catch (ScriptException e) {
            currNum.setText(errorText);
        }
        currCalc.setVisibility(View.INVISIBLE);
    }

    public void btn_clear(View view) {
        if (currNum.getText().toString().equals(getResources().getString(R.string.currentNumberText))) {
            currCalc.setText(getResources().getString(R.string.currentCalculationText));
            prevCalc.setText(getResources().getString(R.string.previousCalculationText));
            autoEval();
            return;
        }
        currNum.setText(getResources().getString(R.string.currentNumberText));
        currCalc.setVisibility(View.INVISIBLE);
    }

    // Functions
    public void btn_rad(View view) {

    }
    public void btn_dec(View view) {

    }
    public void btn_sqrt(View view) {

    }
    public void btn_cbrt(View view) {

    }
    public void btn_log(View view) {

    }
    public void btn_ln(View view) {

    }
    public void btn_squared(View view) {

    }
    public void btn_cubed(View view) {

    }
    public void btn_2powerX(View view) {

    }
    public void btn_XpowerY(View view) {

    }
    public void btn_ePowerX(View view) {

    }
    public void btn_invert(View view) {

    }
    public void btn_factorial(View view) {

    }
    public void btn_abs(View view) {

    }

    // Trig
    public void btn_sin(View view) {

    }
    public void btn_cos(View view) {

    }
    public void btn_tan(View view) {

    }
    public void btn_arcSin(View view) {

    }
    public void btn_arcCos(View view) {

    }
    public void btn_arcTan(View view) {

    }
    public void btn_hyperSin(View view) {

    }
    public void btn_hyperCos(View view) {

    }
    public void btn_hyperTan(View view) {

    }
    public void btn_arcHyperSin(View view) {

    }
    public void btn_arcHyperCos(View view) {

    }
    public void btn_arcHyperTan(View view) {

    }

    // Identities
    public void btn_e(View view) {
        addText("e");
        autoEval();
    }
    public void btn_pi(View view) {
        addText("π");
        autoEval();
    }

    public void autoEval() {
        if (!currNum.getText().toString().equals(getResources().getString(R.string.currentNumberText)))
            try {
                currCalc.setText(evaluate(normalize(currNum)));
                currCalc.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                currCalc.setVisibility(View.INVISIBLE);
            }
    }

    public String evaluate(String expression) throws ScriptException {
        ScriptEngine mathCalc = new ScriptEngineManager().getEngineByName("rhino");
        String result = mathCalc.eval(expression).toString();

        BigDecimal decimal = new BigDecimal(result);
        BigDecimal one = new BigDecimal("1.0");

        if(decimal.remainder(one).equals(one.subtract(one)))
            return decimal.setScale(0).toPlainString();
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    public String normalize(TextView view) {
        return view.getText().toString().replaceAll(getResources().getString(R.string.divideText), "/").replaceAll(getResources().getString(R.string.piText), "3.1415926536");
    }


    public void btn_swap(View view) {
        if (btnSin.getVisibility() == View.VISIBLE) {
            btnSquareRoot.setVisibility(View.GONE);
            btnCubeRoot.setVisibility(View.VISIBLE);

            btnSin.setVisibility(View.GONE);
            btnCos.setVisibility(View.GONE);
            btnTan.setVisibility(View.GONE);
            btnInverseSin.setVisibility(View.VISIBLE);
            btnInverseCos.setVisibility(View.VISIBLE);
            btnInverseTan.setVisibility(View.VISIBLE);

            btnNaturalLog.setVisibility(View.GONE);
            btnLog.setVisibility(View.GONE);
            btnInvert.setVisibility(View.GONE);
            btnHyperSin.setVisibility(View.VISIBLE);
            btnHyperCos.setVisibility(View.VISIBLE);
            btnHyperTan.setVisibility(View.VISIBLE);

            btneExponent.setVisibility(View.GONE);
            btnSquared.setVisibility(View.GONE);
            btnXPowerY.setVisibility(View.GONE);
            btnInverseHyperSin.setVisibility(View.VISIBLE);
            btnInverseHyperCos.setVisibility(View.VISIBLE);
            btnInverseHyperTan.setVisibility(View.VISIBLE);

            btnAbsoluteValue.setVisibility(View.GONE);
            btnPi.setVisibility(View.GONE);
            btne.setVisibility(View.GONE);
            btn2PowerX.setVisibility(View.VISIBLE);
            btnCubed.setVisibility(View.VISIBLE);
            btnFactorial.setVisibility(View.VISIBLE);
            return;
        }
        btnSquareRoot.setVisibility(View.VISIBLE);
        btnCubeRoot.setVisibility(View.GONE);

        btnSin.setVisibility(View.VISIBLE);
        btnCos.setVisibility(View.VISIBLE);
        btnTan.setVisibility(View.VISIBLE);
        btnInverseSin.setVisibility(View.GONE);
        btnInverseCos.setVisibility(View.GONE);
        btnInverseTan.setVisibility(View.GONE);

        btnNaturalLog.setVisibility(View.VISIBLE);
        btnLog.setVisibility(View.VISIBLE);
        btnInvert.setVisibility(View.VISIBLE);
        btnHyperSin.setVisibility(View.GONE);
        btnHyperCos.setVisibility(View.GONE);
        btnHyperTan.setVisibility(View.GONE);

        btneExponent.setVisibility(View.VISIBLE);
        btnSquared.setVisibility(View.VISIBLE);
        btnXPowerY.setVisibility(View.VISIBLE);
        btnInverseHyperSin.setVisibility(View.GONE);
        btnInverseHyperCos.setVisibility(View.GONE);
        btnInverseHyperTan.setVisibility(View.GONE);

        btnAbsoluteValue.setVisibility(View.VISIBLE);
        btnPi.setVisibility(View.VISIBLE);
        btne.setVisibility(View.VISIBLE);
        btn2PowerX.setVisibility(View.GONE);
        btnCubed.setVisibility(View.GONE);
        btnFactorial.setVisibility(View.GONE);
    }



}