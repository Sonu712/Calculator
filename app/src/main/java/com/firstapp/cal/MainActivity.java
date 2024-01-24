package com.firstapp.cal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView resultTv, solutionTv;
    MaterialButton buttonC , buttonBrackOpen,buttonBrackClose ;
    MaterialButton buttonDivide , buttonMultip1y , buttonPlus, buttonMinus, buttonEquals ;
    MaterialButton buttonO , button1, button2 , button3 , button4 , button5 , button6, button7 , button8 ,button9 ;
    MaterialButton buttonAC, buttonDot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        assignId(buttonC, (long) R.id.button_c);
        assignId(buttonBrackOpen, (long) R.id.button_open_bracket);
        assignId(buttonBrackClose,(long) R.id.button_close_bracket);
        assignId(buttonDivide,(long) R.id.button_divide);
        assignId(buttonMultip1y,(long) R.id.button_multiply);
        assignId(buttonPlus,(long) R.id.button_plus);
        assignId(buttonMinus,(long) R.id.button_minus);
        assignId(buttonEquals,(long) R.id.button_equqls);
        assignId(buttonO , (long) R.id.button_0);
        assignId(button1,(long) R.id.button_1);
        assignId(button2,(long) R.id.button_2);
        assignId(button3,(long) R.id.button_3);
        assignId(button4,(long) R.id.button_4);
        assignId(button5,(long) R.id.button_5);
        assignId(button6,(long) R.id.button_6);
        assignId(button7,(long) R.id.button_7);
        assignId(button8,(long) R.id.button_8);
        assignId(button9,(long) R.id.button_9);
        assignId(buttonAC,(long) R.id.button_ac);
        assignId(buttonDot,(long) R.id.button_dot);
        solutionTv.setText("");
        resultTv.setText("0");

    }
    void assignId(MaterialButton btn , Long  id){
        btn= (MaterialButton) findViewById(Math.toIntExact(id));
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(dataToCalculate.length()==0)
            {
                solutionTv.setText("");
                resultTv.setText("0");
            return;}
            else if(dataToCalculate.length()==1){
                solutionTv.setText("");
                resultTv.setText("0");
                return;

            }else{
                dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
            }
        }
        else{
            dataToCalculate =dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch(Exception e)   {
            return "err";
        }
    }
}