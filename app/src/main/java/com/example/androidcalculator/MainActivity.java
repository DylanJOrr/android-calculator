package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Text Views
    TextView currNum, currCalc, prevCalc;

    // Bracket Stack
    Stack<String> bracket;

    // Temp strings
    String prevCalcValue, add, minus, multiply, divide, defaultNum, defaultPrev, defaultCurr, errorText, zeroText, oneText, twoText, threeText, fourText, fiveText, sixText, sevenText, eightText, nineText;

    // Booleans
    private boolean showPrevCalc;
    private Button btnSin, btnCos, btnTan, btnInverseSin, btnInverseCos, btnInverseTan, btnHyperSin, btnHyperCos, btnHyperTan, btnInverseHyperSin, btnInverseHyperCos, btnInverseHyperTan, btnSquareRoot, btnCubeRoot, btnLog, btnNaturalLog, btnInvert, btne, btnPi, btneExponent, btnSquared, btnCubed, btnXPowerY, btnAbsoluteValue, btn2PowerX, btnFactorial, btnDeg, btnRad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        bracket = new Stack<>();

        showPrevCalc = false;

        currNum = findViewById(R.id.current_number);
        currCalc = findViewById(R.id.current_calculation);
        prevCalc = findViewById(R.id.previous_calculation);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(currNum, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        btnSquareRoot = findViewById(R.id.button_squareRoot);
        btnSquareRoot.setOnClickListener(this::btn_sqrt);
        btnCubeRoot = findViewById(R.id.button_cubeRoot);
        btnCubeRoot.setOnClickListener(this::btn_cbrt);
        btnSin = findViewById(R.id.button_sin);
        btnSin.setOnClickListener(this::btn_sin);
        btnInverseSin = findViewById(R.id.button_inverseSin);
        btnInverseSin.setOnClickListener(this::btn_arcSin);
        btnCos = findViewById(R.id.button_cos);
        btnCos.setOnClickListener(this::btn_cos);
        btnInverseCos = findViewById(R.id.button_inverseCos);
        btnInverseCos.setOnClickListener(this::btn_arcCos);
        btnTan = findViewById(R.id.button_tan);
        btnTan.setOnClickListener(this::btn_tan);
        btnInverseTan = findViewById(R.id.button_inverseTan);
        btnInverseTan.setOnClickListener(this::btn_arcTan);
        btnHyperSin = findViewById(R.id.button_hyperSin);
        btnHyperSin.setOnClickListener(this::btn_hyperSin);
        btnHyperCos = findViewById(R.id.button_hyperCos);
        btnHyperCos.setOnClickListener(this::btn_hyperCos);
        btnInvert = findViewById(R.id.button_invert);
        btnHyperTan = findViewById(R.id.button_hyperTan);
        btnHyperTan.setOnClickListener(this::btn_hyperTan);
        btnInverseHyperSin = findViewById(R.id.button_inverseHyperSin);
        btnInverseHyperSin.setOnClickListener(this::btn_arcHyperSin);
        btnInverseHyperCos = findViewById(R.id.button_inverseHyperCos);
        btnInverseHyperCos.setOnClickListener(this::btn_arcHyperCos);
        btnInverseHyperTan = findViewById(R.id.button_inverseHyperTan);
        btnInverseHyperTan.setOnClickListener(this::btn_arcHyperTan);
        btnInvert.setOnClickListener(this::btn_invert);
        btnNaturalLog = findViewById(R.id.button_naturalLog);
        btnNaturalLog.setOnClickListener(this::btn_ln);
        btnLog = findViewById(R.id.button_log);
        btnLog.setOnClickListener(this::btn_log);
        btne = findViewById(R.id.button_e);
        btne.setOnClickListener(this::btn_e);
        btneExponent = findViewById(R.id.button_eExponent);
        btneExponent.setOnClickListener(this::btn_ePowerX);
        btnSquared = findViewById(R.id.button_squared);
        btnSquared.setOnClickListener(this::btn_squared);
        btnCubed = findViewById(R.id.button_cubed);
        btnCubed.setOnClickListener(this::btn_cubed);
        btnXPowerY = findViewById(R.id.button_XPowerY);
        btnXPowerY.setOnClickListener(this::btn_XpowerY);
        btn2PowerX = findViewById(R.id.button_2PowerX);
        btn2PowerX.setOnClickListener(this::btn_2powerX);
        btnPi = findViewById(R.id.button_pi);
        btnPi.setOnClickListener(this::btn_pi);
        btnFactorial = findViewById(R.id.button_factorial);
        btnFactorial.setOnClickListener(this::btn_factorial);
        btnDeg = findViewById(R.id.button_Mode_Dec);
        btnDeg.setOnClickListener(this::btn_deg);
        btnRad = findViewById(R.id.button_Mode_Rad);
        btnRad.setOnClickListener(this::btn_rad);

        btnAbsoluteValue = findViewById(R.id.button_absoluteValue);
        btnAbsoluteValue.setOnClickListener(this::btn_abs);
//        btnAbsoluteValue.setOnLongClickListener(view -> {
//            btn_abs_longClick(view);
//            return true;
//        });

        Button btnAdd = findViewById(R.id.button_add);
        btnAdd.setOnClickListener(this::btn_add);
        Button btnSubtract = findViewById(R.id.button_minus);
        btnSubtract.setOnClickListener(this::btn_subtract);
        Button btnMultiply = findViewById(R.id.button_multiply);
        btnMultiply.setOnClickListener(this::btn_multiply);
        Button btnDivide = findViewById(R.id.button_divide);
        btnDivide.setOnClickListener(this::btn_divide);
        Button btnEquals = findViewById(R.id.button_equals);
        btnEquals.setOnClickListener(this::btn_equals);

        Button btn0 = findViewById(R.id.button_0);
        btn0.setOnClickListener(this::btn_0);
        Button btn1 = findViewById(R.id.button_1);
        btn1.setOnClickListener(this::btn_1);
        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(this::btn_2);
        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(this::btn_3);
        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(this::btn_4);
        Button btn5 = findViewById(R.id.button_5);
        btn5.setOnClickListener(this::btn_5);
        Button btn6 = findViewById(R.id.button_6);
        btn6.setOnClickListener(this::btn_6);
        Button btn7 = findViewById(R.id.button_7);
        btn7.setOnClickListener(this::btn_7);
        Button btn8 = findViewById(R.id.button_8);
        btn8. setOnClickListener(this::btn_8);
        Button btn9 = findViewById(R.id.button_9);
        btn9.setOnClickListener(this::btn_9);

        Button btnClear = findViewById(R.id.button_clear);
        btnClear.setOnClickListener(this::btn_clear);
        Button btnDecimal = findViewById(R.id.button_decimal);
        btnDecimal.setOnClickListener(this::btn_dot);
        Button btnPercent = findViewById(R.id.button_percent);
        btnPercent.setOnClickListener(this::btn_percent);
        Button btnSign = findViewById(R.id.button_sign);
        btnSign.setOnClickListener(this::btn_sign);
        Button btnBracket = findViewById(R.id.button_bracket);
        btnBracket.setOnClickListener(this::btn_brackets);

        ImageButton btnBackspace = findViewById(R.id.button_backspace);
        btnBackspace.setOnClickListener(this::btn_backspace);
        ImageButton btnSwap = findViewById(R.id.button_swap);
        btnSwap.setOnClickListener(this::btn_swap);

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
        if (str.equals(errorText))
            return;
        if (str.equals(defaultNum)) {
            if (text.equals(getResources().getString(R.string.decimalText))) {
                currNum.setText("0.");
                return;
            }
            if (text.equals("^"))
                return;
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
            case "×(":
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
                for (int i = str.length()-1; i >= 0; i--) {
                    switch (str.charAt(str.length() - i)) {
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
            case "π":
            case "e":
                if (!str.endsWith("("))
                    text = multiply+text;
                break;
            case "^":
                int ascii = str.charAt(str.length()-1);
                if (ascii < 48 || ascii > 57)
                    return;
                str+=text;
                currNum.setText(str);
                addBracket(true);
                return;
        }
        str+=text;
        currNum.setText(str);
    }

    private void addFunc(String text) {
        String str = currNum.getText().toString();
        if (str.equals(defaultNum) || str.endsWith("("))
            addText(text);
        else
            addText(multiply + text);
        addBracket(true);
    }

    private void addBracket(boolean forceOverride) {
        String str = currNum.getText().toString();
        if (str.equals(defaultNum)) {
            addText("(");
            bracket.push("(");
            return;
        }

        int ascii = str.charAt(str.length() - 1);
        if (ascii < 48 || ascii > 57) { // If does not end with a number
            if (str.endsWith("(")) { // Ends with an open bracket
                addText("(");
                bracket.push("(");
            } else { // Does not end with an open bracket
                if (!bracket.isEmpty() && !forceOverride) {
                    addText(")");
                    bracket.pop();
                } else {
                    if (forceOverride)
                        addText("(");
                    else
                        addText(multiply + "(");
                    bracket.push("(");
                }
            }
        } else { // Ends with a number
            if (!bracket.isEmpty()) { // Exists an standalone open bracket
                addText(")");
                bracket.pop();
            } else {
                addText(multiply + "(");
                bracket.push("(");
            }
        }
        autoEval();
    }

    private void surroundText(String text) {
        String str = currNum.getText().toString();
        int ascii = str.charAt(str.length()-1);
        //
        if (ascii < 48 || ascii > 57)
            return;
        else if (text.equals(getResources().getString(R.string.absoluteValueText)))
            str = "|"+str+"|";

        currNum.setText(str);
        autoEval();
    }

    @Override
    public void onClick(View view) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        autoEval();
    }

    // Numbers
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

    // Identities
    public void btn_e(View view) {
        addText("e");
        autoEval();
    }
    public void btn_pi(View view) {
        addText("π");
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
            result = evaluate(currNum.getText().toString());
            currNum.setText(result);
            if (showPrevCalc) {
                prevCalc.setText(prevCalcValue);
                prevCalc.setVisibility(View.VISIBLE);
            }
            showPrevCalc = true;
            prevCalcValue = result;
            autoEval();
        } catch (Exception e) {
            currNum.setText(errorText);
        }
        currCalc.setVisibility(View.INVISIBLE);
    }
    public void btn_clear(View view) {
        bracket.clear();
        prevCalcValue = defaultPrev;
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
    public void btn_sqrt(View view) {
        addFunc(getResources().getString(R.string.squareRootText));
        autoEval();
    }
    public void btn_cbrt(View view) {
        addFunc(getResources().getString(R.string.cubeRootText));
        autoEval();
    }
    public void btn_log(View view) {
        addFunc(getResources().getString(R.string.logText));
    }
    public void btn_ln(View view) {
        addFunc(getResources().getString(R.string.naturalLogText));
    }
    public void btn_squared(View view) {
        addText("^");
        if (currNum.getText().toString().endsWith("^(")) {
            addText("2");
            addBracket(false);
        }
        autoEval();
    }
    public void btn_cubed(View view) {
        addText("^");
        if (currNum.getText().toString().endsWith("^(")) {
            addText("2");
            addBracket(false);
        }

        autoEval();
    }
    public void btn_2powerX(View view) {
        addFunc("2^");
    }
    public void btn_XpowerY(View view) {
        addText("^");
    }
    public void btn_ePowerX(View view) {
        addFunc(getResources().getString(R.string.ePowerXText));
    }
    public void btn_invert(View view) {
        addFunc(getResources().getString(R.string.invertText));
    }
    public void btn_factorial(View view) {
        addText("!");
        autoEval();
    }
    public void btn_abs(View view) {
        addFunc("abs");
        autoEval();
    }
    public void btn_abs_longClick(View view) {
        surroundText(getResources().getString(R.string.absoluteValueText));
        autoEval();
    }

    // Trig
    public void btn_rad(View view) {
        mXparser.setRadiansMode();
        btnRad.setVisibility(View.GONE);
        btnDeg.setVisibility(View.VISIBLE);
    }
    public void btn_deg(View view) {
        mXparser.setDegreesMode();
        btnDeg.setVisibility(View.GONE);
        btnRad.setVisibility(View.VISIBLE);
    }

    public void btn_sin(View view) {
        addFunc(getResources().getString(R.string.SinText_Func));
    }
    public void btn_cos(View view) {
        addFunc(getResources().getString(R.string.CosText_Func));
    }
    public void btn_tan(View view) {
        addFunc(getResources().getString(R.string.TanText_Func));
    }
    public void btn_arcSin(View view) {
        addFunc(getResources().getString(R.string.ArcSinText_Func));
    }
    public void btn_arcCos(View view) {
        addFunc(getResources().getString(R.string.ArcCosText_Func));
    }
    public void btn_arcTan(View view) {
        addFunc(getResources().getString(R.string.ArcTanText_Func));
    }
    public void btn_hyperSin(View view) {
        addFunc(getResources().getString(R.string.HyperSinText_Func));
    }
    public void btn_hyperCos(View view) {
        addFunc(getResources().getString(R.string.HyperCosText_Func));
    }
    public void btn_hyperTan(View view) {
        addFunc(getResources().getString(R.string.HyperTanText_Func));
    }
    public void btn_arcHyperSin(View view) {
        addFunc(getResources().getString(R.string.HyperArcSinText_Func));
    }
    public void btn_arcHyperCos(View view) {
        addFunc(getResources().getString(R.string.HyperArcCosText_Func));
    }
    public void btn_arcHyperTan(View view) {
        addFunc(getResources().getString(R.string.HyperArcTanText_Func));
    }

    // Calculations
    public void autoEval() {
        String str = currNum.getText().toString();
        if (!str.equals(defaultNum))
            try {
                currCalc.setText(evaluate(str));
                currCalc.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                currCalc.setText(defaultCurr);
            }
    }
    public String evaluate(String str) {
        str = normalize(str);
        Expression expression = new Expression(str);
        String result = String.valueOf(expression.calculate());

        BigDecimal decimal = new BigDecimal(result);
        BigDecimal one = new BigDecimal("1.0");

        if(decimal.remainder(one).equals(one.subtract(one)))
            return decimal.setScale(0, RoundingMode.UNNECESSARY).toPlainString();
        return decimal.setScale(5, RoundingMode.HALF_UP).toPlainString();
    }

    // Misc
    private String normalize(String str){
        while (str.indexOf('|') != -1) {
            str = str.replaceFirst("\\|", "abs(");
            str = str.replaceFirst("\\|", ")");
        }
        return str.replaceAll("log", "log10");
    }
    public void btn_dot(View view) {
        addText(".");
        autoEval();
    }
    public void btn_brackets(View view) {
        addBracket(false);
        autoEval();
    }
    public void btn_percent(View view) {
        addText("÷100");
        currNum.setText(evaluate(currNum.getText().toString()));
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