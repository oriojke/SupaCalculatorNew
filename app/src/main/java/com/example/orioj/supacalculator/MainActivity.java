package com.example.orioj.supacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Float firstArg, secondArg;
    Character operator;
    boolean eqFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] idList = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6,
                        R.id.num7, R.id.num8, R.id.num9, R.id.numDot, R.id.opDiv, R.id.opMinus, 
                        R.id.opMult, R.id.opPercent, R.id.opPlus, R.id.backspace, R.id.clear, R.id.eq};
        for (int id:idList)
        {
            Button btn = (Button)findViewById(id);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.num0:
                addNumberSymbol('0');
                break;
            case R.id.num1:
                addNumberSymbol('1');
                break;
            case R.id.num2:
                addNumberSymbol('2');
                break;
            case R.id.num3:
                addNumberSymbol('3');
                break;
            case R.id.num4:
                addNumberSymbol('4');
                break;
            case R.id.num5:
                addNumberSymbol('5');
                break;
            case R.id.num6:
                addNumberSymbol('6');
                break;
            case R.id.num7:
                addNumberSymbol('7');
                break;
            case R.id.num8:
                addNumberSymbol('8');
                break;
            case R.id.num9:
                addNumberSymbol('9');
                break;
            case R.id.numDot:
                addNumberSymbol('.');
                break;
            case R.id.opDiv:
                setOperator('/');
                break;
            case R.id.opMinus:
                setOperator('-');
                break;
            case R.id.opMult:
                setOperator('X');
                break;
            case R.id.opPercent:
                //setOperator('%');
                break;
            case R.id.opPlus:
                setOperator('+');
                break;
            case R.id.eq:
                execExpression();
                break;
            case R.id.backspace:
                backspace();
                break;
            case R.id.clear:
                clearExpression();
                break;
            default:
                break;
        }
    }

    private void addNumberSymbol(Character s)
    {
        TextView tv = (TextView)findViewById(R.id.result);
        if(eqFlag)
        {
            clearExpression();
            eqFlag = false;
        }
        if(s == '.' && tv.getText().toString().contains(".")) return;
        tv.append(s.toString());
    }

    private void setOperator(Character op)
    {
        eqFlag = false;
        TextView tv = (TextView)findViewById(R.id.result);
        firstArg = Float.valueOf(tv.getText().toString());
        operator = op;
        tv.setText("");
    }

    private void execExpression()
    {
        TextView tv = (TextView)findViewById(R.id.result);
        if(firstArg == null || tv.getText().length() <= 0) return;
        secondArg = Float.valueOf(tv.getText().toString());
        Float result;
        //tv.setText(firstArg.toString() + operator + secondArg.toString());
        switch (operator)
        {
            case '+':
                result = firstArg + secondArg;
                break;
            case '-':
                result = firstArg - secondArg;
                break;
            case 'X':
                result = firstArg * secondArg;
                break;
            case '/':
                result = firstArg / secondArg;
                break;
            default:
                return;
        }
        tv.setText(result.toString());
        eqFlag = true;
    }

    private void backspace()
    {
        if(eqFlag) return;
        TextView tv = (TextView)findViewById(R.id.result);
        String val = tv.getText().toString();
        if(val.length() > 0)
            tv.setText(val.substring(0, val.length() - 1));
    }

    private void clearExpression()
    {
        firstArg = null;
        secondArg = null;
        operator = null;
        eqFlag = false;
        TextView tv = (TextView)findViewById(R.id.result);
        tv.setText("");
    }
}
