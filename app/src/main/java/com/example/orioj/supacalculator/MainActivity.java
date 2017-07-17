package com.example.orioj.supacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Double firstArg, secondArg;
    Character operator;
    boolean eqFlag = false;
    TextView mResultTextView;
    String[] strComponents;

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
        
        mResultTextView = (TextView)findViewById(R.id.result);
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
                percent();
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
        if(eqFlag)
        {
            clearExpression();
            eqFlag = false;
        }
        strComponents = mResultTextView.getText().toString().split("\n");
        if(strComponents[strComponents.length - 1].length() == 13 ||
                (s.equals('.') && strComponents[strComponents.length - 1].length() == 12)) return;
        if(s.equals('.') && strComponents[strComponents.length - 1].contains(".")) return;
        if(s.equals('.') && strComponents[strComponents.length - 1].length() == 0) mResultTextView.append("0");
        mResultTextView.append(s.toString());
    }

    private void setOperator(Character op)
    {
        if(!eqFlag) execExpression();
        eqFlag = false;
        String resultViewText = mResultTextView.getText().toString();
        if(resultViewText.isEmpty()) return;
        strComponents = resultViewText.split("\n");
        firstArg = Double.valueOf(strComponents[strComponents.length - 1]);
        operator = op;
        mResultTextView.setText(strComponents[strComponents.length - 1] + "\n" + op + "\n");
    }

    private void execExpression()
    {
        strComponents = mResultTextView.getText().toString().split("\n");
        if(firstArg == null ||strComponents[strComponents.length - 1].length() <= 0) return;
        secondArg = Double.valueOf(strComponents[strComponents.length - 1]);
        Double result;
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
        String resString = result.toString();
        resString = resString.substring(0, Math.min(resString.length(), 13));
        if(!resString.isEmpty() && resString.endsWith("."))
            resString = resString.substring(0, Math.min(resString.length(), 12));
        mResultTextView.append("\n" + resString);
        eqFlag = true;
    }

    private void backspace()
    {
        if(eqFlag) return;
        String val = mResultTextView.getText().toString();
        if((val.length() > 0) && (val.lastIndexOf('\n') != (val.length() - 1)))
            mResultTextView.setText(val.substring(0, val.length() - 1));
    }

    private void clearExpression()
    {
        firstArg = null;
        secondArg = null;
        operator = null;
        eqFlag = false;
        mResultTextView.setText("");
    }

    private void percent()
    {
        strComponents = mResultTextView.getText().toString().split("\n");
        if(firstArg == null || strComponents[strComponents.length - 1].length() == 0) return;
        Double newSecondArg = (Double.valueOf(strComponents[strComponents.length - 1].toString()) / 100) * firstArg;
        mResultTextView.setText("");
        for(int i = 0; i < strComponents.length-1; i++)
            mResultTextView.append(strComponents[i]+"\n");
        mResultTextView.append(newSecondArg.toString());
    }
}
